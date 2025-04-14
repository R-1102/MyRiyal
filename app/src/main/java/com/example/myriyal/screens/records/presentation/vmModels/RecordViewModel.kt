package com.example.myriyal.screens.records.presentation.vmModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.records.domain.useCases.RecordUseCases
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// ViewModel for the Record feature.
// Connects UI (RecordScreen) with the domain use cases.
//
// Responsibilities:
// - Expose state to the UI
// - Trigger use case functions based on user actions


//@HiltViewModel
class RecordViewModel(private val useCases: RecordUseCases) : ViewModel() {

    // Expose all records as StateFlow
    val records: StateFlow<List<RecordEntity>> = useCases
        .getAllRecords()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Insert a new record (triggered from UI)
    fun insert(record: RecordEntity) = viewModelScope.launch {
        useCases.insert(record)
    }

    // Update an existing record
    fun update(record: RecordEntity) = viewModelScope.launch {
        useCases.update(record)
    }

    // Delete a specific record
    fun delete(record: RecordEntity) = viewModelScope.launch {
        useCases.delete(record)
    }

    // Optionally expose a way to get a record by ID (used in detail/edit screens)
    suspend fun getById(id: Int): RecordEntity? {
        return useCases.getRecordById(id)
    }
}