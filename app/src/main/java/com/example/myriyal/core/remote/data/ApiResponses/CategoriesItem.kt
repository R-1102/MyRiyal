package com.example.myriyal.core.remote.data.ApiResponses

import com.google.gson.annotations.SerializedName

data class CategoriesItem(
    @SerializedName("name")
    val name : String,

    @SerializedName ("color")
    val color : String,

    @SerializedName ("icon")
    val icon : String,

    @SerializedName ("budget_limit")
    val budgetLimit : Int
)
