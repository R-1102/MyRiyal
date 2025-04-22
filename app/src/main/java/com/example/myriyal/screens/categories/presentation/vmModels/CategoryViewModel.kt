package com.example.myriyal.screens.categories.presentation.vmModels

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.entities.TrackerEntity
import com.example.myriyal.core.local.enums.CategoryStatus
import com.example.myriyal.core.local.enums.CategoryType
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import com.example.myriyal.screens.categories.domian.useCases.CategoryUseCases
import com.example.myriyal.screens.categories.domian.useCases.InsertTrackerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
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

    var categoryBudgetAmount = mutableStateOf("0.0")

    var startDate by mutableStateOf<Long?>(null)

    // Currently selected category from dropdown
    val selectedCategory = mutableStateOf<CategoryEntity?>(null)

    // Tracker toggle state
    val enableTracker = mutableStateOf(false)

    // Tracker budget input as string (validated before saving)
    val trackerBudget = mutableStateOf("")

    // Tracker start date (used if tracker is enabled)
    var trackerStartDate = mutableStateOf<Long?>(null)


    val showDatePicker = mutableStateOf(false)


    // Input change functions, setting user input from UI in here to do the business logic
     fun onCategoryIconChange(value: String) {
         categoryIcon = value

        val getTrackerForCategory: suspend (Int) -> TrackerEntity?
        val getSpentForCategory: (Int) -> StateFlow<Double>

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

    fun insertWithTracker(category: CategoryEntity) {
        viewModelScope.launch {
            useCases.insertWithTracker(
                category = category,
                trackerBudget = trackerBudget.value,
                trackerStartDate = trackerStartDate.value
            )
        }
    }



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

    @Inject
    lateinit var insertTrackerUseCase: InsertTrackerUseCase

    fun createCategoryWithOptionalTracker(category: CategoryEntity) {
        // Inserts a new category and creates a tracker if budget > 0
        viewModelScope.launch {
            val categoryId = useCases.insert(category)
            val budgetAmount = trackerBudget.value.toDoubleOrNull() ?: 0.0
            if (budgetAmount > 0.0){
                Log.d("CategoryViewModel", "Tracker → categoryId: $categoryId, budget: $budgetAmount, start: ${trackerStartDate.value}")
                val tracker = TrackerEntity(
                    categoryId = categoryId.toInt(),
                    budgetAmount = budgetAmount,
                    startDate = trackerStartDate.value ?: System.currentTimeMillis(),
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis()
                )
                insertTrackerUseCase(tracker)
            }
        }
    }

    fun updateCategoryWithOptionalTracker(category: CategoryEntity) {
        // Updates a category and updates or creates its tracker depending on existence
        viewModelScope.launch {
            useCases.update(category)

            val budgetAmount = trackerBudget.value.toDoubleOrNull() ?: 0.0
            if (budgetAmount > 0.0) {
                val existingTracker = useCases.getTrackerByCategoryId(category.categoryId)

                if (existingTracker != null) {
                    val updatedTracker = existingTracker.copy(
                        budgetAmount = budgetAmount,
                        startDate = trackerStartDate.value ?: System.currentTimeMillis(),
                        updatedAt = System.currentTimeMillis()
                    )
                    useCases.updateTracker(updatedTracker)
                } else {
                    val newTracker = TrackerEntity(
                        categoryId = category.categoryId,
                        budgetAmount = budgetAmount,
                        startDate = trackerStartDate.value ?: System.currentTimeMillis(),
                        createdAt = System.currentTimeMillis(),
                        updatedAt = System.currentTimeMillis()
                    )
                    useCases.insertTracker(newTracker)
                }
            }
        }
    }




    fun getSpentForCategory(categoryId: Int): StateFlow<Double> {
        // Returns a reactive stream of total amount spent for the given category
        return useCases.getSpentAmount(categoryId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)
    }


    fun getTrackerForCategory(categoryId: Int): StateFlow<TrackerEntity?> {
        // Returns a reactive stream of tracker data for the given category ID
        return flow {
            emit(useCases.getTrackerByCategoryId(categoryId))
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
    }

    suspend fun getTracker(categoryId: Int): TrackerEntity? {
        // Returns tracker data once (non-reactive), used during form population
        return useCases.getTrackerByCategoryId(categoryId)
    }






}
