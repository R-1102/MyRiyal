package com.example.myriyal.screens.records.presentation.vmModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.records.domain.model.RecordFilterType
import com.example.myriyal.screens.records.domain.useCases.RecordUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel for the Record feature.
 *
 * Responsibilities:
 * - Acts as a middle layer between the UI and domain use cases
 * - Manages state for filtering records (e.g. by day/week/month/year)
 * - Handles insertion, updating, deletion, and lookup of records
 *
 * Layer Interaction:
 * UI → RecordViewModel → RecordUseCases → Repository → Room DAO
 */
class RecordViewModel(
    private val useCases: RecordUseCases
) : ViewModel() {

    // --------------------------
    // Filter State
    // --------------------------

    // Holds the current selected filter type (default is ALL)
    private val _filter = MutableStateFlow(RecordFilterType.ALL)
    val filter: StateFlow<RecordFilterType> = _filter

    // --------------------------
    // Record State (Filtered)
    // --------------------------

    /**
     * Emits a filtered list of records based on the selected filter type (day/week/month/year/all).
     * The flow automatically recomputes when either the list of records OR the filter value changes.
     *
     * Used by: RecordScreen UI
     */
    val records: StateFlow<List<RecordEntity>> = combine(
        useCases.getAllRecords(), // Fetches all records from database
        _filter // Current selected filter (day, week, etc.)
    ) { records, filterType ->
        val now = System.currentTimeMillis()
        when (filterType) {
            RecordFilterType.DAY -> records.filter { isSameDay(it.date, now) }
            RecordFilterType.WEEK -> records.filter { isSameWeek(it.date, now) }
            RecordFilterType.MONTH -> records.filter { isSameMonth(it.date, now) }
            RecordFilterType.YEAR -> records.filter { isSameYear(it.date, now) }
            RecordFilterType.ALL -> records
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    /**
     * Updates the filter type.
     * This will automatically re-filter the records list.
     */
    fun setFilter(type: RecordFilterType) {
        _filter.value = type
    }

    // --------------------------
    // Record Actions
    // --------------------------

    /**
     * Inserts a new record.
     * Called from: RecordScreen form submission (Add)
     */
    fun insert(record: RecordEntity) = viewModelScope.launch {
        useCases.insert(record)
    }

    /**
     * Updates an existing record.
     * Called from: RecordScreen form submission (Edit)
     */
    fun update(record: RecordEntity) = viewModelScope.launch {
        useCases.update(record)
    }

    /**
     * Deletes a record.
     * Called from: RecordItem (Delete button)
     */
    fun delete(record: RecordEntity) = viewModelScope.launch {
        useCases.delete(record)
    }

    /**
     * Retrieves a record by ID.
     * Useful for editing a record or showing details.
     */
    suspend fun getById(id: Int): RecordEntity? {
        return useCases.getRecordById(id)
    }

    // --------------------------
    // Date Filter Utilities
    // --------------------------

    private fun isSameDay(time1: Long, time2: Long): Boolean {
        val oneDayMillis = 1000 * 60 * 60 * 24
        return (time1 / oneDayMillis) == (time2 / oneDayMillis)
    }

    private fun isSameWeek(time1: Long, time2: Long): Boolean {
        val oneWeekMillis = 1000 * 60 * 60 * 24 * 7
        return (time1 / oneWeekMillis) == (time2 / oneWeekMillis)
    }


    /**
     * Checks if two timestamps fall in the same calendar month and year.
     *
     * @param time1 The first timestamp in milliseconds.
     * @param time2 The second timestamp in milliseconds.
     * @return True if both timestamps are in the same month and year.
     */
    private fun isSameMonth(time1: Long, time2: Long): Boolean {
        val cal1 = java.util.Calendar.getInstance().apply {
            timeInMillis = time1
        }

        val cal2 = java.util.Calendar.getInstance().apply {
            timeInMillis = time2
        }

        return cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR) &&
                cal1.get(java.util.Calendar.MONTH) == cal2.get(java.util.Calendar.MONTH)
    }


    /**
     * Checks if two timestamps fall in the same calendar year.
     *
     * @param time1 The first timestamp in milliseconds.
     * @param time2 The second timestamp in milliseconds.
     * @return True if both timestamps are in the same year.
     */
    private fun isSameYear(time1: Long, time2: Long): Boolean {
        val cal1 = java.util.Calendar.getInstance().apply {
            timeInMillis = time1
        }

        val cal2 = java.util.Calendar.getInstance().apply {
            timeInMillis = time2
        }

        return cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR)
    }

}
