package com.example.myriyal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myriyal.screens.authentication.presentation.screens.BalanceScreen
import com.example.myriyal.screens.authentication.presentation.screens.ForgotPassword
import com.example.myriyal.screens.authentication.presentation.screens.LoginScreen
import com.example.myriyal.screens.authentication.presentation.screens.NewPassword
import com.example.myriyal.screens.authentication.presentation.screens.SignUpScreen
import com.example.myriyal.screens.authentication.presentation.screens.SignUpScreenCopy
import com.example.myriyal.screens.authentication.presentation.screens.SplashScreen
import com.example.myriyal.screens.categories.presentation.screens.AddCategory
import com.example.myriyal.screens.categories.presentation.screens.CategoryScreen
import com.example.myriyal.screens.records.presentation.screens.RecordScreen
import com.example.myriyal.screens.records.presentation.screens.ViewRecordScreen

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
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route, // splash screen must be the start
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
            SignUpScreen(navController)
        }

        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.LogIn.route) {
            LoginScreen(navController)
        }

        composable(Screen.Balance.route){
            BalanceScreen(navController)
        }

        composable(Screen.ForgotPass.route){
            ForgotPassword(navController)
        }
        composable(Screen.NewPass.route) {
            NewPassword(navController)
        }

        composable(Screen.AddCategory.route) {
            AddCategory()
        }

        composable(Screen.ViewRecord.route) {
            ViewRecordScreen()
        }
    }
}
