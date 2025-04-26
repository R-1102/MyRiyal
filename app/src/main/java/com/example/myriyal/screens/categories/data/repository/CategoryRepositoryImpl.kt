package com.example.myriyal.screens.categories.data.repository

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.utils.ConnectivityStatus
import com.example.myriyal.screens.categories.data.dataSources.CategoryDataSource
import com.example.myriyal.screens.categories.data.local.CategoryDao
import com.example.myriyal.screens.categories.data.mapper.toDto
import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.categories.data.model.toDto
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val localCategoryDataSource: CategoryDataSource,
    private val remoteCategoryDataSource: CategoryDataSource,
    private val connectivityStatus: ConnectivityStatus,
    private val dao: CategoryDao
) : CategoryRepository {

    // Fetches a local category using its server ID.
    // Used for matching local and remote data.
    override suspend fun getCategoryByServerId(serverId: String): CategoryEntity? {
        return localCategoryDataSource.getCategoryByServerId(serverId)
    }

    // Inserts a new category into the database.
    // If connected to the internet, it first uploads the category to remote (API).
    // Then saves it locally with isSync = true if uploaded remotely, otherwise false.
    override suspend fun insertCategory(category: CategoryEntity): Long {
        var isUploadedRemotely = false

        if (connectivityStatus.isConnected()) {
            isUploadedRemotely = remoteCategoryDataSource.postCategory(category.toDto())
        }

        val localCategory = category.copy(isSync = isUploadedRemotely)
        val localCategoryId = localCategoryDataSource.insertCategory(localCategory)

        Log.d("RepositoryInsert", "Inserted category with ID: $localCategoryId")

        return localCategoryId
    }

    // Updates an existing category.
    // TODO: Implement remote update later if needed.
    override suspend fun updateCategory(category: CategoryEntity) {
        if (connectivityStatus.isConnected()) {
            // TODO: Implement remote update
        } else {
            localCategoryDataSource.insertCategory(category)
        }
    }

    // Marks a category as INACTIVE (soft delete).
    // TODO: Implement remote soft delete if needed.
    override suspend fun softDeleteCategory(categoryId: Int) {
        if (connectivityStatus.isConnected()) {
            // TODO: Implement remote soft delete
            remoteCategoryDataSource.softDeleteCategory(categoryId)
        }
        // Always update local
        localCategoryDataSource.softDeleteCategory(categoryId)

    }

    // Returns a reactive flow of all categories.
    // If online, fetch remote categories and insert any missing ones into local DB.
    override fun getAllCategories(): Flow<List<CategoryEntity>> = flow {
        if (connectivityStatus.isConnected()) {
            try {
                val remoteCategories = remoteCategoryDataSource.getAllCategories().first()

                remoteCategories.forEach { remoteCategory ->
                    val serverId = remoteCategory.serverId
                    val existingEntity = serverId?.let { id ->
                        localCategoryDataSource.getCategoryByServerId(id)
                    }

                    if (existingEntity == null) {
                        // Insert into local DB only if it doesn't exist
                        val newEntity = remoteCategory
                        localCategoryDataSource.insertCategory(newEntity)

                        Log.d("RepositoryInsert", "Inserted NEW category: ${newEntity.name}")
                    } else {
                        Log.d("RepositoryInsert", "Category already exists: ${existingEntity.name}")
                    }
                }
            } catch (e: Exception) {
                Log.e("RepositoryError", "Failed to fetch or insert remote categories: ${e.message}")
            }
        }

        // Always emit from local DB
        emitAll(localCategoryDataSource.getAllCategories())
    }.flowOn(Dispatchers.IO)

    // Deletes a category permanently (local and TODO: remote).
    override suspend fun deleteCategory(category: CategoryEntity) {
        if (connectivityStatus.isConnected()) {
            remoteCategoryDataSource.deleteCategory(category)
        } else {
            // TODO: Always delete from local
        }
        localCategoryDataSource.deleteCategory(category)

    }

    // Seeds predefined categories on first app launch.
    // Uploads to remote server if online, then always inserts into local.
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
