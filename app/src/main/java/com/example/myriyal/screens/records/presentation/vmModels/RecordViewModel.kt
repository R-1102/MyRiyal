package com.example.myriyal.screens.records.presentation.vmModels


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.records.data.local.RecordEntity
import com.example.myriyal.screens.records.domain.model.RecordFilterType
import com.example.myriyal.screens.records.domain.useCases.RecordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

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

@HiltViewModel
class RecordViewModel @Inject constructor(
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
    // --------------------------
    // Search State (NEWLY ADDED)
    // --------------------------

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    /**
     * Called when the user types into the search bar.
     * Updates the internal search query state which triggers filtering.
     */
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    /**
     * Combined logic for search and filter.
     *
     * First:
     * - If the search query is blank → return all records
     * - Otherwise → run a search query on names using LIKE
     *
     * Second:
     * - Applies the selected time filter (DAY, WEEK, etc.)
     *
     * Result:
     * - Emits a final list of records that match both search and time filters
     */
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    val records: StateFlow<List<RecordEntity>> = combine(
        _searchQuery.flatMapLatest { query ->
            if (query.isBlank()) {
                useCases.getAllRecords() // Get all if nothing typed
            } else {
                useCases.searchRecordsByName(query) // Otherwise filter by name
            }
        },      // Fetches all records from database
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

// --------------------------
// Form State (Managed inside ViewModel)
// --------------------------

    // Holds the input value for record name
    val name = mutableStateOf("")

    // Holds the input value for record description
    val description = mutableStateOf("")

    // Holds the input value for record amount
    val amount = mutableStateOf("")

    // Currently selected category from dropdown
    val selectedCategory = mutableStateOf<CategoryEntity?>(null)

    // Currently selected record (used when editing)
    val selectedRecord = mutableStateOf<RecordEntity?>(null)

// --------------------------
// Form Validation & Submission
// --------------------------

    /**
     * Checks if the form fields are valid.
     * Required: name, amount, and selected category.
     */
    fun isFormValid(): Boolean {
        return name.value.isNotBlank() && amount.value.isNotBlank() && selectedCategory.value != null
    }

    /**
     * Submits the form by either inserting or updating a record.
     * Builds a [RecordEntity] from form state and resets the form after saving.
     */
    fun submitRecord() {
        val category = selectedCategory.value ?: return
        val timestamp = System.currentTimeMillis()

        val record = RecordEntity(
            recordId = selectedRecord.value?.recordId ?: 0, // Use existing ID if editing
            name = name.value,
            description = description.value,
            amount = amount.value.toDoubleOrNull() ?: 0.0,
            categoryId = category.categoryId,
            date = selectedRecord.value?.date ?: timestamp,
            createdAt = selectedRecord.value?.createdAt ?: timestamp,
            updatedAt = timestamp
        )

        viewModelScope.launch {
            if (selectedRecord.value == null) insert(record) // Insert new record
            else update(record)                              // Update existing one

            resetForm() // Clear form after submission
        }
    }

    /**
     * Populates the form with values from an existing record.
     * Used when editing a record from the list.
     */
    fun populateForm(record: RecordEntity, categories: List<CategoryEntity>) {
        selectedRecord.value = record
        name.value = record.name
        description.value = record.description ?: ""
        amount.value = record.amount.toString()
        selectedCategory.value = categories.find { it.categoryId == record.categoryId }
    }

    /**
     * Resets the form fields to initial blank values.
     * Called after submission or canceling edit.
     */
    fun resetForm() {
        selectedRecord.value = null
        name.value = ""
        description.value = ""
        amount.value = ""
        selectedCategory.value = null
    }

    // --------------------------
    // Total Balance (Live Tracker)
    // --------------------------
    val balance: StateFlow<Double> = useCases.getTotalBalance()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)
}
