package com.example.myriyal.screens.categories.presentation.vmModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.enums.CategoryStatus
import com.example.myriyal.screens.categories.domian.useCases.CategoryUseCases
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// ViewModel for the Category feature.
// This class belongs to the presentation layer and acts as the middle layer between the UI and the domain layer (use cases).
//
// Primary responsibilities:
// - Hold and expose UI state (categories list)
// - Handle user actions such as insert, update, delete
// - Coordinate with use cases (domain layer) to execute business logic
//
// Data flow:
// - Receives state: from CategoryUseCases → Repository → DAO
// - Sends state: to CategoryScreen (UI layer)
// - Triggers logic: insert, update, delete, seed, etc.

class CategoryViewModel(private val useCases: CategoryUseCases) : ViewModel() {

    // Exposes a filtered list of ACTIVE categories using StateFlow.
    // This list is observed in the UI and updates automatically when the database changes.
    //
    // Flow:
    // Repository.getAllCategories() → UseCase → ViewModel
    // Applies filter to exclude soft-deleted categories (status != ACTIVE)
    val categories: StateFlow<List<CategoryEntity>> = useCases
        .getAll()
        .map { list -> list.filter { it.status == CategoryStatus.ACTIVE } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Triggered when a user adds a new category in the UI.
    // This could be a user-defined or predefined category.
    fun insert(category: CategoryEntity) = viewModelScope.launch {
        useCases.insert(category)
    }

    // Triggered when a user edits a category.
    // Updates name, color, type, or other editable fields.
    fun update(category: CategoryEntity) = viewModelScope.launch {
        useCases.update(category)
    }

    // Triggered when user chooses to "Deactivate" a category.
    // Instead of deleting it from the database, it updates the status to INACTIVE.
    fun softDelete(categoryId: Int) = viewModelScope.launch {
        useCases.softDelete(categoryId)
    }

    // Triggered when user clicks "Delete Forever".
    // This permanently removes the category from the database.
    fun delete(category: CategoryEntity) = viewModelScope.launch {
        useCases.delete(category)
    }

    // Seeds the database with predefined categories (e.g., Food, Salary).
    // This runs on first launch (or each launch, depending on logic) from the UI screen.
    fun seedPredefinedCategories() = viewModelScope.launch {
        useCases.seed()
    }
}
