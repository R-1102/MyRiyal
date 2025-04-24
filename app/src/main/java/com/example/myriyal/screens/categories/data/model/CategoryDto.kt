package com.example.myriyal.screens.categories.data.model

import com.google.gson.annotations.SerializedName

// For Request and Response of the API
data class CategoryDto(
    val name : String,
    val color : String,
    val icon : String?,
    @SerializedName ("budget_limit")//to match para name in the json
    val budgetLimit : Int?
)