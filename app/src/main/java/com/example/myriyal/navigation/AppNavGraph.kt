package com.example.myriyal.navigation

import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.myriyal.screens.authentication.presentation.screens.BalanceScreen
import com.example.myriyal.screens.authentication.presentation.screens.LoginScreen
import com.example.myriyal.screens.authentication.presentation.screens.SignUpScreen
import com.example.myriyal.screens.authentication.presentation.screens.SplashScreen


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        enterTransition = {
            slideInVertically(
                initialOffsetY = { it }, // Enter from bottom
                animationSpec = tween(900)
            )
        },
        popExitTransition = {
            slideOutVertically(
                targetOffsetY = { it }, // Exit to bottom
                animationSpec = tween(900)
            )
        },
    )
    {
        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }
        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }
        composable(Routes.SIGNUP) {
            SignUpScreen(navController)
        }
        composable(Routes.BALANCE) {
            BalanceScreen(navController)
        }

    }
}