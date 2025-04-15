package com.example.myriyal.navigation

sealed class Screen(val route: String) {
    object Category : Screen("category_screen")
    object Record : Screen("record_screen")
}