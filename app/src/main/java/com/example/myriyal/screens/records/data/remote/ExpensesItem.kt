package com.example.myriyal.screens.records.data.remote

import com.google.gson.annotations.SerializedName

data class ExpensesItem(
    @SerializedName ("amount")
    val amount : Int,

    @SerializedName ("category_id")
    val categoryId : String,

    @SerializedName ("description")
    val description : String,

    @SerializedName ("data")
    val date : String
)
