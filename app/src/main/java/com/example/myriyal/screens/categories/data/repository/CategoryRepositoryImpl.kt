package com.example.myriyal.screens.categories.data.repository




import android.content.Context
import android.util.Log
import com.example.myriyal.core.local.data.PredefinedCategoryProvider
import com.example.myriyal.core.local.db.DatabaseProvider
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.enums.CategoryStatus

import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(
    private val context: Context
) : CategoryRepository {

    private val dao = DatabaseProvider.getDatabase(context).categoryDao()

    override suspend fun insertCategory(category: CategoryEntity) {
        dao.insertCategory(category)
    }

    override suspend fun updateCategory(category: CategoryEntity) {
        dao.updateCategory(category)
    }

    override fun getAllCategories(): Flow<List<CategoryEntity>> {
        return dao.getAllCategories()
    }

    override suspend fun softDeleteCategory(categoryId: Int) {
        dao.updateCategoryStatus(categoryId, CategoryStatus.INACTIVE)
    }

    override suspend fun deleteCategory(category: CategoryEntity) {
        dao.deleteCategory(category)
    }

    override suspend fun seedPredefinedCategories() {
        val existing = dao.getAllCategoriesOnce()
        val existingNames = existing.map { it.name }

        val predefined = PredefinedCategoryProvider.getCategories()
        val newOnes = predefined.filter { it.name !in existingNames }

        newOnes.forEach { dao.insertCategory(it) }
    }


}
