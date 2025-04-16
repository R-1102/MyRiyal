package com.example.myriyal.navigation

sealed class Screen(val route: String) {
    object Category : Screen("category_screen")
    object Record : Screen("record_screen")
    object AddCategory : Screen("addCategory_screen")
    object SignUp : Screen("SignUp_Screen")

}