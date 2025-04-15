package com.example.myriyal.screens.categories.data.repository

import android.content.Context
import android.util.Log
import com.example.myriyal.core.local.data.PredefinedCategoryProvider
import com.example.myriyal.core.local.db.DatabaseProvider
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.enums.CategoryStatus
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

// Implementation of the CategoryRepository interface.
// This class acts as the data layer for category-related operations.
// It interacts directly with the DAO and provides data to the ViewModel.
//
// Data flows:
// - Called by: CategoryViewModel
// - Sends data to: CategoryDao (via DatabaseProvider)
// - Used in: CategoryScreen (via ViewModel)


//@Inject

class CategoryRepositoryImpl(
    private val context: Context
) : CategoryRepository {

    // Provides access to DAO from the Room database
    private val dao = DatabaseProvider.getDatabase(context).categoryDao()

    // Inserts a new category into the database
    // Called by: CategoryViewModel.insert()
    override suspend fun insertCategory(category: CategoryEntity) {
        dao.insertCategory(category)
    }

    // Updates an existing category
    // Called by: CategoryViewModel.update()
    override suspend fun updateCategory(category: CategoryEntity) {
        dao.updateCategory(category)
    }

    // Retrieves all categories as a Flow (reactive stream)
    // Called by: CategoryViewModel.categories
    override fun getAllCategories(): Flow<List<CategoryEntity>> {
        return dao.getAllCategories()
    }

    // Soft deletes a category by updating its status to INACTIVE
    // Called by: CategoryViewModel.softDelete()
    override suspend fun softDeleteCategory(categoryId: Int) {
        dao.updateCategoryStatus(categoryId, CategoryStatus.INACTIVE)
    }

    // Completely deletes a category from the database
    // Called by: CategoryViewModel.delete()
    override suspend fun deleteCategory(category: CategoryEntity) {
        dao.deleteCategory(category)
    }

    // Seeds predefined categories into the database (on app start or first load)
    // Called by: CategoryViewModel.seedPredefinedCategories()
    // Filters out any predefined categories that already exist (by name)
    override suspend fun seedPredefinedCategories() {
        val existing = dao.getAllCategoriesOnce()
        val existingNames = existing.map { it.name }

        val predefined = PredefinedCategoryProvider.getCategories()
        val newOnes = predefined.filter { it.name !in existingNames }

        // Inserts only predefined categories that do not already exist
        newOnes.forEach { dao.insertCategory(it) }
    }
}
