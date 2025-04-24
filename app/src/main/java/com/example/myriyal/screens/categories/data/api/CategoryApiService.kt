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
    suspend fun getCategories(): Response<List<CategoryDto>>

    // insert a new category
    // From local --> Remote
    @POST ("/categories")
    suspend fun postCategory(
        @Header("authorization") token : String = "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjkwOTg1NzhjNDg4MWRjMDVlYmYxOWExNWJhMjJkOGZkMWFiMzRjOGEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vZXhwZW5zZS10cmFja2VyLTc4YjA3IiwiYXVkIjoiZXhwZW5zZS10cmFja2VyLTc4YjA3IiwiYXV0aF90aW1lIjoxNzQ1NDg0ODUwLCJ1c2VyX2lkIjoiUXVGaElyWXlyb1V4RnkwVzBwU0hTbEhzeVJDMiIsInN1YiI6IlF1RmhJcll5cm9VeEZ5MFcwcFNIU2xIc3lSQzIiLCJpYXQiOjE3NDU0ODQ4NTAsImV4cCI6MTc0NTQ4ODQ1MCwiZW1haWwiOiJsZWVuMjAyNUBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsibGVlbjIwMjVAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.Ff88U0IV5qfK_t_xnmzPZ_8s0tmjkGkOuaDo3IEvIbRE_ryqUSbo0wZI5-unlkzCSsUz3B-Zsv5CaW2aCNbgQ6PyWVt4EArGAIxPh2EfTiSe4eb0uf17ODpLYCWflSHH040ApPYVa7BRR48eRImxHkyLG428UREyEgt_U_svwO_oFSFdE5bjeJvcK_uOkzUFTC2MKD8LxN5ki2ADZ8HC4njdB9HUyUfLrDvftLf8wWwMWRfDhpHPfxlg9SGHFVtIqMKgTSX7UU63WNSkP6Mmd7UUJS4olzeIXppLO_nbIlIARTTXk_6N7jtnFAiDyHS62rU7CMuUimIjdA53lk3VXg",
        @Body category: CategoryDto
    ) : Response<CategoryResponse>

}