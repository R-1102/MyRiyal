package com.example.myriyal.core.remote.data.ApiResponses

import com.google.gson.annotations.SerializedName

data class BudgetsItem(
    @SerializedName("name")
    val name : String,

    @SerializedName("amount")
    val amount : Int,

    @SerializedName("category_id")
    val categoryId : String,

    @SerializedName("start_date")
    val startDate : String,

    @SerializedName("end_date")
    val endDate : String
)
