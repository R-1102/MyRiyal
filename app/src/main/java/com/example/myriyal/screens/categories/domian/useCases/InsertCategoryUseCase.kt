package com.example.myriyal.screens.categories.domian.useCases

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository

// Use case for inserting a new category into the database.
// This represents a unit of business logic responsible for saving user-defined or predefined categories.
//
// Called from: CategoryViewModel → viewModelScope.launch { useCases.insert(category) }
// Sends data to: CategoryRepository.insertCategory()
// Final destination: DAO inserts a CategoryEntity into the Room database

class InsertCategoryUseCase(
    private val repository: CategoryRepository
) {
    // Enables calling this use case like a function
    suspend operator fun invoke(category: CategoryEntity): Long {
        return repository.insertCategory(category)
    }

}
