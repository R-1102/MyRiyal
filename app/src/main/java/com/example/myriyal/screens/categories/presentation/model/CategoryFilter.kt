package com.example.myriyal.screens.categories.presentation.model

import com.example.myriyal.core.local.enums.CategoryType

sealed class CategoryFilter {
    object All : CategoryFilter()
    data class ByType(val type: CategoryType) : CategoryFilter()
//    object HasBudget : CategoryFilter()
//    object NoBudget : CategoryFilter()
}