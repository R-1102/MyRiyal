package com.example.myriyal.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myriyal.screens.Profile.presentattion.screens.ViewProfile
import com.example.myriyal.screens.authentication.presentation.screens.BalanceScreen
import com.example.myriyal.screens.authentication.presentation.screens.ForgotPassword
import com.example.myriyal.screens.authentication.presentation.screens.LoginScreen
import com.example.myriyal.screens.authentication.presentation.screens.NewPassword
import com.example.myriyal.screens.authentication.presentation.screens.SignUpScreen
import com.example.myriyal.screens.authentication.presentation.screens.SplashScreen
import com.example.myriyal.screens.categories.presentation.screens.CategoryForm
import com.example.myriyal.screens.categories.presentation.screens.ViewCategoryScreen
import com.example.myriyal.screens.records.presentation.screens.RecordScreen
import com.example.myriyal.screens.records.presentation.screens.ViewRecordScreen

/**
 * Defines app-level navigation for the main features:
 * - Category screen
 * - Record screen
 * - Home screen
 * - Statistic screen
 * - and others
 */

/* if the home screen created make sure that
if we enter the Home screen the login and signup
screen doesn't pop out to it again*/

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ViewCategory.route,
        modifier = modifier
    ) {
        // Authentication Screens with animation -- maybe for the home screen too (slide in) --
        composable(
            route = Screen.SplashScreen.route, /*SplashScreen*/
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(900)
                )
            },
        ) {
            SplashScreen(navController)
        }

        composable(
            route = Screen.SignUp.route,
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(900)
                )
            },
        ) {
            SignUpScreen(navController)
        }

        composable(
            route = Screen.Balance.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(600)
                )
            },

            ) {
            BalanceScreen(navController)
        }

        composable(
            route = Screen.ForgotPass.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(600)
                )
            },
        ) {
            ForgotPassword(navController)
        }

        composable(
            route = Screen.NewPass.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(600)
                )
            },
        ) {
            NewPassword(navController)
        }

        //Screens without animation
        composable(route = Screen.LogIn.route) {
            LoginScreen(navController)
        }

        composable(Screen.ViewCategory.route) {
            ViewCategoryScreen()
        }

//        composable(Screen.AddCategory.route) {
//            CategoryForm(
//                viewModel.selectedCategory.value, {
//                viewModel.selectedCategory.value = null
//                shouldShowDialog.value = false
//            }) {
//                viewModel.selectedCategory.value = null
//                shouldShowDialog.value = false
//            }
//        }

        composable(Screen.Record.route) {
            RecordScreen()
        }

        composable(Screen.ViewRecord.route) {
            ViewRecordScreen()
        }

        composable(Screen.ViewProfile.route) {
            ViewProfile()
        }
    }
}
