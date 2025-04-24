package com.example.myriyal.screens.categories.data.dataSources

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.categories.data.model.CategoryDto
import kotlinx.coroutines.flow.Flow

interface CategoryDataSource {

    // Inserts a new category into the database.
    // Called by: ViewModel.insert()
    suspend fun insertCategory(category: CategoryEntity)

    //DO WE NEED TO ADD NEW INSERTION FOR API SINCE THE PARA IS DIFFERENT?
    suspend fun postCategory(category: CategoryDto)

    // Updates an existing category.
    // Called by: ViewModel.update()
    suspend fun updateCategory(category: CategoryEntity)

    // Marks a category as INACTIVE (soft delete).
    // Called by: ViewModel.softDelete()
    suspend fun softDeleteCategory(categoryId: Int)

    // Returns a reactive list of all categories.
    // Used by: ViewModel.categories (StateFlow)
    suspend fun getAllCategories(): Flow<List<CategoryEntity>>

    // Permanently deletes a category from the database.
    // Called by: ViewModel.delete()
    suspend fun deleteCategory(category: CategoryEntity)

    // Seeds predefined categories into the database (called once during app setup).
    // Called by: ViewModel.seedPredefinedCategories()
    suspend fun seedPredefinedCategories()


}
