package com.example.myriyal.screens.categories.domian.useCases

import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository

// Use case for permanently deleting a category from the database.
// This is typically used for user-initiated hard deletion actions.

// Use case for permanently deleting a category from the database

class DeleteCategoryUseCase(
    private val repository: CategoryRepository
) {
    // Enables use case to be called like a function
    suspend operator fun invoke(category: CategoryEntity) {
        repository.deleteCategory(category)
    }
}
