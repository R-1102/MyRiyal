package com.example.myriyal.screens.categories.domian.repository

import com.example.myriyal.core.local.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun insertCategory(category: CategoryEntity)
    suspend fun updateCategory(category: CategoryEntity)
    suspend fun softDeleteCategory(categoryId: Int)
    fun getAllCategories(): Flow<List<CategoryEntity>>
    suspend fun deleteCategory(category: CategoryEntity)
    suspend fun seedPredefinedCategories()
}
