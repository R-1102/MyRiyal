package com.example.myriyal.screens.categories.data.dataSources

import android.util.Log
import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.categories.data.remote.CategoryApiService
import com.example.myriyal.screens.categories.data.local.PredefinedCategoryProvider
import com.example.myriyal.screens.categories.data.model.CategoriesResponse
import com.example.myriyal.screens.categories.data.model.CategoryDto
import com.example.myriyal.screens.categories.data.mapper.toDto
import com.example.myriyal.screens.categories.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * RemoteCategoryDataSource handles communication with the remote API for category operations.
 * It fetches categories, posts new categories, and seeds predefined categories to the server.
 */
class RemoteCategoryDataSource @Inject constructor(
    private val api: CategoryApiService
) : CategoryDataSource {

    // Fetches all categories from the remote server and maps them to CategoryEntity
    override fun getAllCategories(): Flow<List<CategoryEntity>> = flow {
        val response = api.getCategories()
        if (response.isSuccessful) {
            val body = response.body()
            val categories = body?.categories ?: emptyList()
            emit(categories.map { it.toEntity(null) }) // Convert DTOs to local Entity
        } else {
            emit(emptyList())
        }
    }



    override suspend fun insertCategory(category: CategoryEntity): Long {
        throw UnsupportedOperationException("Not supported in remote")
    }

    // Posts a single category to the remote server
    override suspend fun postCategory(category: CategoryDto): Boolean {
        val response = api.postCategory(category = category)
        return response.isSuccessful
    }

    override suspend fun updateCategory(category: CategoryEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun softDeleteCategory(categoryId: Int) {

    }

//    override fun getAllCategories(): Flow<List<CategoryEntity>> = kotlinx.coroutines.flow.flow {
//        val response = api.getCategories()
//        if (response.isSuccessful) {
//            val categories = response.body() ?: emptyList()
//            emit(categories.map { it.toEntity(null) })
//        } else {
//            emit(emptyList())
//        }
//    }







    override suspend fun deleteCategory(category: CategoryEntity) {
        TODO("Not yet implemented")
    }

    // Seeds predefined categories to the remote server
    // Only posts those that do not already exist remotely
    override suspend fun seedPredefinedCategories() {
        try {
            // 1. Fetch existing remote categories
            val response = api.getCategories()

            if (response.isSuccessful) {
                val existingRemoteCategories = response.body()?.categories ?: emptyList()
                val existingNames = existingRemoteCategories.map { it.name }

                // 2. Get predefined categories from local provider
                val predefined = PredefinedCategoryProvider.getCategories()

                // 3. Filter only the new ones that don't exist remotely
                val newPredefined = predefined.filter { it.name !in existingNames }

                // 4. Post the new predefined categories
                newPredefined.forEach { predefinedCategory ->
                    val success = api.postCategory(predefinedCategory.toDto())
                    if (success.isSuccessful) {
                        Log.d("SeedPredefined", " Posted new category: ${predefinedCategory.name}")
                    } else {
                        Log.e("SeedPredefined", " Failed to post category: ${predefinedCategory.name}")
                    }
                }
            } else {
                Log.e("SeedPredefined", " Failed to fetch existing categories. Code: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("SeedPredefined", " Exception during seeding predefined categories: ${e.message}")
        }
    }


    override suspend fun getCategoryByServerId(serverId: String): CategoryEntity? {
        throw UnsupportedOperationException("Remote doesn't support getCategoryByServerId")
    }

}
