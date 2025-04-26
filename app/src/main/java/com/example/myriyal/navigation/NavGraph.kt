package com.example.myriyal.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myriyal.screens.home.HomeScreen
import com.example.myriyal.screens.profile.presentattion.screens.ViewProfile
import com.example.myriyal.screens.authentication.presentation.screens.BalanceScreen
import com.example.myriyal.screens.authentication.presentation.screens.ForgotPassword
import com.example.myriyal.screens.authentication.presentation.screens.LoginScreen
import com.example.myriyal.screens.authentication.presentation.screens.NewPassword
import com.example.myriyal.screens.authentication.presentation.screens.SignUpScreen
import com.example.myriyal.screens.authentication.presentation.screens.SplashScreen
import com.example.myriyal.screens.authentication.presentation.vmModels.NotificationViewModel
import com.example.myriyal.screens.categories.presentation.screens.ViewCategoryScreen
import com.example.myriyal.screens.graphsRepresentation.presentation.screens.ViewStatisticScreen
import com.example.myriyal.screens.records.presentation.screens.RecordScreen
import com.example.myriyal.screens.records.presentation.screens.ViewRecordScreen
import com.google.firebase.auth.FirebaseAuth

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
    modifier: Modifier,
    firebaseAuth: FirebaseAuth
) {
    val notificationViewModel: NotificationViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = if (notificationViewModel.getStoredFcmToken().isNullOrEmpty()) {
            Screen.Home.route
        } else {
            Screen.Home.route
        },
        modifier = modifier
    ) {
        // Authentication Screens with animation
        composable(
            route = Screen.SplashScreen.route, /*SplashScreen*/
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(900)
                )
            },
        ) {
            SplashScreen(navController,firebaseAuth)
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

        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.ViewCategory.route) {
            ViewCategoryScreen()
        }

        composable(Screen.Record.route) {
            RecordScreen()
        }

        composable(Screen.ViewRecord.route) {
            ViewRecordScreen()
        }

        composable(Screen.ViewProfile.route) {
            ViewProfile()
        }

        composable(Screen.Statistic.route) {
            ViewStatisticScreen()
        }
    }
}
