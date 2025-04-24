package com.example.myriyal.screens.categories.data.dataSources

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.categories.data.local.CategoryDao
import com.example.myriyal.screens.categories.data.local.PredefinedCategoryProvider
import com.example.myriyal.core.local.enums.CategoryStatus
import com.example.myriyal.screens.categories.data.model.CategoryDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalCategoryDataSource @Inject constructor(
    private val dao: CategoryDao
// Injected DAO for accessing Room database
) : CategoryDataSource {

    // Inserts a new category into the database
    // Called by: InsertCategoryUseCase
    override suspend fun insertCategory(category: CategoryEntity): Long {
        return dao.insertCategory(category)
    }

    override suspend fun postCategory(category: CategoryDto): Boolean {
        throw UnsupportedOperationException("Not supported in local")
    }

    // Updates an existing category
    // Called by: UpdateCategoryUseCase
    override suspend fun updateCategory(category: CategoryEntity) {
        dao.updateCategory(category)
    }

    // Retrieves all categories as a reactive Flow
    // Called by: GetAllCategoriesUseCase
    override suspend fun getAllCategories(): Flow<List<CategoryEntity>> {
        return dao.getAllCategories()
    }

    // Soft-deletes a category by setting its status to INACTIVE
    // Called by: SoftDeleteCategoryUseCase
    override suspend fun softDeleteCategory(categoryId: Int) {
        dao.updateCategoryStatus(categoryId, CategoryStatus.INACTIVE)
    }

    // Permanently deletes a category from the database
    // Called by: DeleteCategoryUseCase
    override suspend fun deleteCategory(category: CategoryEntity) {
        dao.deleteCategory(category)
    }

    // Seeds predefined categories (e.g., Food, Salary) into the database
    // Only inserts those that don’t already exist (checked by name)
    // Called by: SeedPredefinedCategoriesUseCase (usually on app launch)
    override suspend fun seedPredefinedCategories() {
        val existing = dao.getAllCategoriesOnce()
        val existingNames = existing.map { it.name }

        val predefined = PredefinedCategoryProvider.getCategories()
        val newOnes = predefined.filter { it.name !in existingNames }

        newOnes.forEach { dao.insertCategory(it) }
    }
}