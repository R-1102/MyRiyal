package com.example.myriyal.screens.categories.data.dataSources

import com.example.myriyal.screens.authentication.domain.useCases.GetFcmTokenUseCase
import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.categories.data.api.CategoryApiService
import com.example.myriyal.screens.categories.data.model.CategoryDto
import com.example.myriyal.screens.categories.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RemoteCategoryDataSource @Inject constructor (
    private val api: CategoryApiService
) : CategoryDataSource {


    override suspend fun insertCategory(category: CategoryEntity) {
        throw UnsupportedOperationException("Not supported in remote")
    }

    override suspend fun postCategory(category: CategoryDto) {
        print("insideRemote CategoryDataSource")
        val response = api.postCategory(category)  // API call that returns a Response
        if (response.isSuccessful) {
            // Handle success (you can log, or do something else if needed)
            print("Category posted successfully")
        } else {
            // Handle error (e.g., network error or bad response)
            throw Exception("Failed to post category: ${response.message()}")
        }
    }

    //        val dto = category.toDto()
//        val response = api.insertCategory(dto)
//        if (!response.isSuccessful) {
//            throw Exception("Remote insert failed: ${response.code()} ${response.message()}")
//        }
    override suspend fun updateCategory(category: CategoryEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun softDeleteCategory(categoryId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCategories(): Flow<List<CategoryEntity>>  {
        val response = api.getCategories()
        return if (response.isSuccessful){

            val categories = response.body()?: emptyList()
            flowOf(categories.map { it.toEntity(null) })
        }else{
            flowOf(emptyList())
        }

    }

    override suspend fun deleteCategory(category: CategoryEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun seedPredefinedCategories() {
        TODO("Not yet implemented")
    }
}