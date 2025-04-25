package com.example.myriyal.screens.categories.domian.repository

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import kotlinx.coroutines.flow.Flow

// Domain-level repository interface for category operations.
// Defines what operations the ViewModel can perform without knowing how they’re implemented.
//
// Data flows:
// - Called by: CategoryViewModel (presentation layer)
// - Implemented by: CategoryRepositoryImpl (data layer)
// - Decouples business logic from storage logic (Room, DAO)


interface CategoryRepository {

    // Inserts a new category into the database.
    // Called by: ViewModel.insert()
    suspend fun insertCategory(category: CategoryEntity): Long

    // Updates an existing category.
    // Called by: ViewModel.update()
    suspend fun updateCategory(category: CategoryEntity)

    // Marks a category as INACTIVE (soft delete).
    // Called by: ViewModel.softDelete()
    suspend fun softDeleteCategory(categoryId: Int)

    // Returns a reactive list of all categories.
    // Used by: ViewModel.categories (StateFlow)
    fun getAllCategories(): Flow<List<CategoryEntity>>

    // Permanently deletes a category from the database.
    // Called by: ViewModel.delete()
    suspend fun deleteCategory(category: CategoryEntity)

    // Seeds predefined categories into the database (called once during app setup).
    // Called by: ViewModel.seedPredefinedCategories()
    suspend fun seedPredefinedCategories()

    fun searchCategoryByName(query:String): Flow<List<CategoryEntity>>
}
