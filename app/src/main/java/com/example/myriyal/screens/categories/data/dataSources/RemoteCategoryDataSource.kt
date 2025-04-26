package com.example.myriyal.screens.categories.data.dataSources

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.categories.data.remote.CategoryApiService
import com.example.myriyal.screens.categories.data.local.PredefinedCategoryProvider
import com.example.myriyal.screens.categories.data.model.CategoryDto
import com.example.myriyal.screens.categories.data.mapper.toDto
import com.example.myriyal.screens.categories.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RemoteCategoryDataSource @Inject constructor(
    private val api: CategoryApiService
) : CategoryDataSource {


    override suspend fun insertCategory(category: CategoryEntity): Long {
        throw UnsupportedOperationException("Not supported in remote")
    }

    override suspend fun postCategory(category: CategoryDto): Boolean {
        print("insideRemote CategoryDataSource")
        val response = api.postCategory(category = category)  // API call that returns a Response

        return response.isSuccessful
    }

    override suspend fun updateCategory(category: CategoryEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun softDeleteCategory(categoryId: Int) {

    }

    override fun getAllCategories(): Flow<List<CategoryEntity>> {
        val response = api.getCategories()
        return if (response.isSuccessful) {

            val categories = response.body() ?: emptyList()
            flowOf(categories.map { it.toEntity(null) })
        } else {
            flowOf(emptyList())
        }

    }

    override suspend fun deleteCategory(category: CategoryEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun seedPredefinedCategories() {
        val predefined = PredefinedCategoryProvider.getCategories()
        predefined.forEach { api.postCategory(it.toDto()) }
    }
}
