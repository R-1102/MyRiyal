package com.example.myriyal.screens.categories.data.api

import com.example.myriyal.screens.categories.data.model.CategoryDto
import com.example.myriyal.screens.categories.data.model.CategoryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface CategoryApiService {

    // Fetch all Categories
    // From Remote  --> local
    @GET("/categories")
    fun getCategories(): Response<List<CategoryDto>>

    // insert a new category
    // From local --> Remote
    @POST ("/categories")
    suspend fun postCategory(
        @Header ("authorization") token : String = "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjkwOTg1NzhjNDg4MWRjMDVlYmYxOWExNWJhMjJkOGZkMWFiMzRjOGEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vZXhwZW5zZS10cmFja2VyLTc4YjA3IiwiYXVkIjoiZXhwZW5zZS10cmFja2VyLTc4YjA3IiwiYXV0aF90aW1lIjoxNzQ1NTEzMzQzLCJ1c2VyX2lkIjoiUXVGaElyWXlyb1V4RnkwVzBwU0hTbEhzeVJDMiIsInN1YiI6IlF1RmhJcll5cm9VeEZ5MFcwcFNIU2xIc3lSQzIiLCJpYXQiOjE3NDU1MTMzNDMsImV4cCI6MTc0NTUxNjk0MywiZW1haWwiOiJsZWVuMjAyNUBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImZpcmViYXNlIjp7ImlkZW50a",
        @Body category: CategoryDto
    ) : Response<CategoryResponse>

}