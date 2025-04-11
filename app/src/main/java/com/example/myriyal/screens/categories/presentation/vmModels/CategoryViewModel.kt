package com.example.myriyal.screens.categories.presentation.vmModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.enums.CategoryStatus
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// ViewModel for the Category feature.
// This is the presentation layer, responsible for exposing state and actions to the UI.
//
// Data flows:
// - Receives data from: CategoryRepository (domain layer)
// - Sends data to: CategoryScreen (UI layer) as StateFlow
// - Handles user actions (insert, update, delete) and triggers repository methods

class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {

    // Exposes a list of active categories as a reactive StateFlow to the UI.
    // Filters out INACTIVE (soft-deleted) categories.
    // Used directly in: CategoryScreen
    val categories: StateFlow<List<CategoryEntity>> = repository
        .stateIn(
        )

    // Inserts a new category (user-created or predefined)
    // Called when user submits the form in the UI
    fun insert(category: CategoryEntity) {
        viewModelScope.launch {
            repository.insertCategory(category)
        }
    }

    // Updates an existing category
    // Called when user edits a category in the UI
    fun update(category: CategoryEntity) {
        viewModelScope.launch {
            repository.updateCategory(category)
        }
    }

    // Soft deletes a category by setting its status to INACTIVE
    // Called when user taps "Deactivate" in the UI
    fun softDelete(categoryId: Int) {
        viewModelScope.launch {
            repository.softDeleteCategory(categoryId)
        }
    }

    // Permanently deletes a category
    // Called when user taps "Delete Forever" in the UI
    fun delete(category: CategoryEntity) {
        viewModelScope.launch {
            repository.deleteCategory(category)
        }
    }

    // Seeds predefined categories on first launch or when needed during development
    // Called from: CategoryScreen using LaunchedEffect(Unit)
    fun seedPredefinedCategories() {
        viewModelScope.launch {
            repository.seedPredefinedCategories()
        }
    }
}
