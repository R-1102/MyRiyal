package com.example.myriyal.screens.categories.data.dataSources

import android.util.Log
import com.example.myriyal.screens.categories.data.local.CategoryDao
import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.categories.data.local.PredefinedCategoryProvider
import com.example.myriyal.screens.categories.domian.model.CategoryStatus
import com.example.myriyal.screens.categories.data.model.CategoryDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Local data source that interacts directly with the Room database (DAO layer).
 * Handles database CRUD operations for categories.
 *
 * Data flows:
 * - Called by: CategoryRepositoryImpl (data layer)
 * - Uses: CategoryDao (Room database access)
 * - Works fully offline (local database only)
 */
class LocalCategoryDataSource @Inject constructor(
    val dao: CategoryDao
    // Injected DAO for accessing Room database
) : CategoryDataSource {

    /**
     * Retrieves a category by its server-assigned ID (remote ID).
     * Used to check if a category already exists locally based on remote server ID.
     */
    override suspend fun getCategoryByServerId(serverId: String): CategoryEntity? {
        return dao.getCategoryByServerId(serverId)
    }

    /**
     * Inserts a new category into the local Room database.
     * Called by: InsertCategoryUseCase
     */
    override suspend fun insertCategory(category: CategoryEntity): Long {
        Log.d("LocalCategoryDataSource", "Inserting category: ${category.name}")
        return dao.insertCategory(category) // Room handles local ID generation
    }

    /**
     * Not supported: posting categories remotely is not allowed from Local source.
     */
    override suspend fun postCategory(category: CategoryDto): Boolean {
        throw UnsupportedOperationException("Not supported in local")
    }

    /**
     * Updates an existing category entity in the database.
     * Called by: UpdateCategoryUseCase
     */
    override suspend fun updateCategory(category: CategoryEntity) {
        dao.updateCategory(category)
    }

    /**
     * Retrieves all categories as a reactive Flow.
     * Used by: GetAllCategoriesUseCase
     */
    override fun getAllCategories(): Flow<List<CategoryEntity>> {
        return dao.getAllCategories() // Fetch from Room
    }

    /**
     * Soft-deletes a category by marking its status as INACTIVE (not removing from DB).
     * Called by: SoftDeleteCategoryUseCase
     */
    override suspend fun softDeleteCategory(categoryId: Int) {
        dao.updateCategoryStatus(categoryId, CategoryStatus.INACTIVE)
    }

    /**
     * Permanently deletes a category from the database.
     * Called by: DeleteCategoryUseCase
     */
    override suspend fun deleteCategory(category: CategoryEntity) {
        dao.deleteCategory(category)
    }

    /**
     * Seeds predefined categories (e.g., Food, Salary) into the local database.
     * Only inserts predefined categories that do not already exist by name.
     * Called by: SeedPredefinedCategoriesUseCase (typically on first app launch)
     */
    override suspend fun seedPredefinedCategories() {
        val existing = dao.getAllCategoriesOnce()
        val existingNames = existing.map { it.name }

        val predefined = PredefinedCategoryProvider.getCategories()
        val newOnes = predefined.filter { it.name !in existingNames }

        newOnes.forEach { dao.insertCategory(it) }
    }
}
