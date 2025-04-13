package com.example.myriyal.screens.records.presentation.vmModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.records.domain.useCases.RecordUseCases
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel for the Record feature.
 *
 * Acts as the bridge between:
 * - UI layer (RecordScreen)
 * - Domain layer (RecordUseCases)
 *
 * Responsibilities:
 * - Exposes reactive state (list of records) to the UI
 * - Handles user actions (insert, update, delete)
 * - Communicates with domain layer via RecordUseCases
 *
 * Data Flow:
 * - UI → ViewModel → UseCases → Repository → DAO
 * - DAO (Room) → Repository → UseCases → ViewModel → UI
 */
class RecordViewModel(
    private val useCases: RecordUseCases
) : ViewModel() {

    /**
     * Exposes a list of all records as a reactive StateFlow.
     *
     * Observed from: RecordScreen
     * Source: Room DB → Repository → UseCase → ViewModel
     */
    val records: StateFlow<List<RecordEntity>> = useCases
        .getAllRecords()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    /**
     * Inserts a new record into the database.
     * Triggered from: RecordScreen form (when Add is pressed)
     * Data goes to: insert → Repository → Room DAO
     */
    fun insert(record: RecordEntity) = viewModelScope.launch {
        useCases.insert(record)
    }

    /**
     * Updates an existing record in the database.
     * Triggered from: RecordScreen form (when Update is pressed)
     * Data goes to: update → Repository → Room DAO
     */
    fun update(record: RecordEntity) = viewModelScope.launch {
        useCases.update(record)
    }

    /**
     * Deletes a record from the database.
     * Triggered from: RecordItem delete button
     * Data goes to: delete → Repository → Room DAO
     */
    fun delete(record: RecordEntity) = viewModelScope.launch {
        useCases.delete(record)
    }

    /**
     * Fetches a specific record by ID.
     * Can be used in: detail screens, preloading forms for edit, etc.
     * Accessed via: getById → UseCase → Repository → Room DAO
     */
    suspend fun getById(id: Int): RecordEntity? {
        return useCases.getRecordById(id)
    }
}
