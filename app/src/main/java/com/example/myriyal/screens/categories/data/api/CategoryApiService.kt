package com.example.myriyal.screens.categories.data.api

import com.example.myriyal.screens.categories.data.model.CategoryDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface CategoryApiService {

    // Fetch all Categories
    // From Remote  --> local
    @GET("/categories")
    suspend fun getCategories(): Response<List<CategoryDto>>

    // insert a new category
    // From local --> Remote
    @POST ("/categories")
    suspend fun postCategory(
        @Header("authorization") token : String,
        @Body category: CategoryDto
    ) : Response<List<CategoryDto>>

}