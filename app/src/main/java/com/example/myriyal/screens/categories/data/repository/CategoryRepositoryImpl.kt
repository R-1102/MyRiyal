package com.example.myriyal.screens.categories.data.repository

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.core.utils.ConnectivityStatus
import com.example.myriyal.screens.categories.data.dataSources.CategoryDataSource
import com.example.myriyal.screens.categories.data.dataSources.LocalCategoryDataSource
import com.example.myriyal.screens.categories.data.local.PredefinedCategoryProvider
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
// - Handle both cases of syncing categories data, when the app is online or not
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
            //remote
        }
        // Local all the time
    }

    override fun getAllCategories(): Flow<List<CategoryEntity>> {
        return localCategoryDataSource.getAllCategories()
    }

    override suspend fun deleteCategory(category: CategoryEntity) {
        if (connectivityStatus.isConnected()) {
        } else {
        }
    }

    override suspend fun seedPredefinedCategories( ) {

        val existing = (localCategoryDataSource as LocalCategoryDataSource).dao.getAllCategoriesOnce()
        val existingNames = existing.map { it.name }

        val predefined = PredefinedCategoryProvider.getCategories()
        val newOnes = predefined.filter { it.name !in existingNames }

        newOnes.forEach { insertCategory(it) }

    }

}



