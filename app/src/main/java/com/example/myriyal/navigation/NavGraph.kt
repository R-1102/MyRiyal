package com.example.myriyal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myriyal.screens.authentication.presentation.screens.SignUpScreen
import com.example.myriyal.screens.authentication.presentation.screens.SignUpScreenCopy
import com.example.myriyal.screens.categories.presentation.screens.AddCategory
import com.example.myriyal.screens.categories.presentation.screens.CategoryScreen
import com.example.myriyal.screens.records.presentation.screens.RecordScreen

/**
 * Defines app-level navigation for the main features:
 * - Category screen
 * - Record screen
 *
 * This is a simplified version using Jetpack Compose's built-in NavHost (no animation).
 */

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AddCateogry.route, // Default screen when app starts
        modifier = modifier
    ) {
        // Category Management Screen
        composable(Screen.Category.route) {
            CategoryScreen()
        }

        // Record Management Screen
        composable(Screen.Record.route) {
            RecordScreen()
        }

        // Sign-Up Management Screen
        composable(Screen.SignUp.route){
            SignUpScreenCopy(navController)
        }

        composable(Screen.AddCateogry.route) {
            AddCategory()
        }
    }
}
