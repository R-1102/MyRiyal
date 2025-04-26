package com.example.myriyal.screens.categories.domian.model

sealed class CategoryFilter {
    object All : CategoryFilter()
    data class ByType(val type: CategoryType) : CategoryFilter()
}