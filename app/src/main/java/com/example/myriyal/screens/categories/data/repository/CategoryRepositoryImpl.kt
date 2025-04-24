package com.example.myriyal.screens.categories.data.repository

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.core.utils.ConnectivityStatus
import com.example.myriyal.screens.categories.data.dataSources.CategoryDataSource
import com.example.myriyal.screens.categories.data.model.toDto
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

// Implementation of the CategoryRepository interface.
// This class acts as the data layer for category-related operations.
// It interacts directly with the DAO and provides data to the ViewModel.
//
// Responsibilities:
// - Called by: UseCases (insert, update, delete, etc.)
// - Sends data to: CategoryDao
// - Provided via Hilt and injected using constructor injection.

class CategoryRepositoryImpl @Inject constructor(
    private val localCategoryDataSource: CategoryDataSource,
    private val remoteCategoryDataSource: CategoryDataSource,
    private val connectivityStatus: ConnectivityStatus
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

//    override suspend fun insertCategory(category: CategoryEntity) {
//        if (network.isConnected()) {
//            remote.insertCategory(category)
//        } else {
//            local.insertCategory(category)
//        }
//    }
//    override suspend fun insertCategory2(){
//        if (connectivityStatus.isConnected()) {
//
//            val category : CategoryDto
//            remoteCategoryDataSource.
//
//        } else {
//            print("it's offline")
//            localCategoryDataSource.insertCategory(category)
//
//
//        }
//
//
//    }

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
        } else {
        }
    }

    override fun getAllCategories(): Flow<List<CategoryEntity>> {
        if (connectivityStatus.isConnected()) {
        } else {
        }

        return flow { }
    }

    override suspend fun deleteCategory(category: CategoryEntity) {
        if (connectivityStatus.isConnected()) {
        } else {
        }
    }

    override suspend fun seedPredefinedCategories() {
        if (connectivityStatus.isConnected()) {
        } else {
        }
    }

}



