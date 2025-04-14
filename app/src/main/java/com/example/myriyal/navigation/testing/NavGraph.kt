package com.example.myriyal.navigation.testing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myriyal.screens.categories.presentation.CategoryScreen
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel

import com.example.myriyal.screens.records.presentation.screens.RecordScreen
import com.example.myriyal.screens.records.presentation.vmModels.RecordViewModel

sealed class Screen(val route: String) {
    object Category : Screen("category_screen")
    object Record : Screen("record_screen")
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    categoryViewModel: CategoryViewModel,
    recordViewModel: RecordViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "category_screen"
    ) {
        composable("category_screen") {
            CategoryScreen(viewModel = categoryViewModel)
        }
        composable("record_screen") {
            RecordScreen(
                viewModel = recordViewModel,
                categoryViewModel = categoryViewModel
            )
        }
    }
}
