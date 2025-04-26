package com.example.myriyal.screens.categories.data.repository

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.utils.ConnectivityStatus
import com.example.myriyal.screens.categories.data.dataSources.CategoryDataSource
import com.example.myriyal.screens.categories.data.local.CategoryDao
import com.example.myriyal.screens.categories.data.mapper.toDto
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val localCategoryDataSource: CategoryDataSource,
    private val remoteCategoryDataSource: CategoryDataSource,
    private val connectivityStatus: ConnectivityStatus,
    private val dao: CategoryDao
) : CategoryRepository {

    // Inserts a new category into the database
    // Called by: InsertCategoryUseCase

    override suspend fun insertCategory(category: CategoryEntity): Long {
        var isUploadedRemotely = false

        // Check for connection
        if (connectivityStatus.isConnected()) {//There is an Internet
            isUploadedRemotely = remoteCategoryDataSource.postCategory(category.toDto())
        }

        // Insert locally first!
        val localCategory = category.copy(isSync = isUploadedRemotely)
        val localCategoryId = localCategoryDataSource.insertCategory(localCategory)

        return localCategoryId
    }

    // Updates an existing category
    // Called by: UpdateCategoryUseCase
    override suspend fun updateCategory(category: CategoryEntity) {
        if (connectivityStatus.isConnected()) {
            //remote (remoteCategoryDataSource.insertRemote)
        } else {
            localCategoryDataSource.insertCategory(category)
        }
    }

    override suspend fun softDeleteCategory(categoryId: Int) {
        if (connectivityStatus.isConnected()) {
            remoteCategoryDataSource.softDeleteCategory(categoryId)
        }
        localCategoryDataSource.softDeleteCategory(categoryId)

    }

    override fun getAllCategories(): Flow<List<CategoryEntity>> {
        return localCategoryDataSource.getAllCategories()
    }

    override suspend fun deleteCategory(category: CategoryEntity) {
        if (connectivityStatus.isConnected()) {
            remoteCategoryDataSource.deleteCategory(category)
        }
        localCategoryDataSource.deleteCategory(category)

    }

    override suspend fun seedPredefinedCategories() {
        if (connectivityStatus.isConnected()) {
            remoteCategoryDataSource.seedPredefinedCategories()
        }
        localCategoryDataSource.seedPredefinedCategories()
    }

    override fun searchCategoryByName(query: String): Flow<List<CategoryEntity>> {
        return dao.searchCategoryByName(query)
    }
}
