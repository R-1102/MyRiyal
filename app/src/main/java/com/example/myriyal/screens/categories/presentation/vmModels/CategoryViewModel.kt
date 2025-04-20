package com.example.myriyal.screens.categories.presentation.vmModels

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.enums.CategoryStatus
import com.example.myriyal.core.local.enums.CategoryType
import com.example.myriyal.screens.categories.domian.useCases.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

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

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCases: CategoryUseCases
) : ViewModel() {

    // -------------------- UI Form State --------------------

    /** Bound to the category name text field in the AddCategory screen */
    val categoryName = mutableStateOf("")

    /** Bound to the category type dropdown (EXPENSE or INCOME) */
    val categoryType = mutableStateOf(CategoryType.EXPENSE)

    /** Bound to the icon selection dropdown (emoji or icon string) */
    var categoryIcon by mutableStateOf("🔥")

    var categoryBudgetAmount by mutableDoubleStateOf(0.0)

    var startDate by mutableStateOf<Long?>(null)

    // Currently selected category from dropdown
    val selectedCategory = mutableStateOf<CategoryEntity?>(null)

    // Input change functions, setting user input from UI in here to do the business logic
     fun onCategoryIconChange(value: String) {
         categoryIcon = value
     }

    // -------------------- State Flow for Categories --------------------

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


    // -------------------- Category Actions --------------------

    /** Triggered when a user adds a new category in the UI */
    fun insert(category: CategoryEntity) = viewModelScope.launch {
        useCases.insert(category)
    }

    /** Triggered when a user edits a category */
    fun update(category: CategoryEntity) = viewModelScope.launch {
        useCases.update(category)
    }

    /** Triggered when user chooses to "Deactivate" a category */
    fun softDelete(categoryId: Int) = viewModelScope.launch {
        useCases.softDelete(categoryId)
    }

    /** Triggered when user clicks "Delete Forever" */
    fun delete(category: CategoryEntity) = viewModelScope.launch {
        useCases.delete(category)
    }

    /** Seeds the database with predefined categories (e.g., Food, Salary) */
    fun seedPredefinedCategories() = viewModelScope.launch {
        useCases.seed()
    }
}
