package com.example.myriyal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myriyal.navigation.NavGraph
import com.example.myriyal.screens.mainScreen.BottomNavigationBar

/**
 * Root Composable function to initialize and launch the MyRiyal app.
 *
 * Responsibilities:
 * - Initializes required dependencies (ViewModels for Category and Record).
 * - Sets up a Scaffold with a bottom navigation bar.
 * - Passes initialized ViewModels and navigation controller to NavGraph.
 *
 * Layers:
 * - UI Layer: Scaffold, NavGraph
 * - Presentation Layer: ViewModels (CategoryViewModel, RecordViewModel)
 * - Domain Layer: UseCases (via ViewModel factories)
 * - Data Layer: Repository (instantiated via utility functions)
 */

@Composable
fun AppNavigation() {

    // Navigation controller for handling screen routing
    val navController = rememberNavController()

    // Observe current back stack entry to determine current screen
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Define routes where the bottom bar should NOT be shown
    val bottomBarHiddenRoutes = listOf("Splash_screen", "Login_screen", "SignUp_Screen","Balance","ForgotPass_Screen","NewPass_Screen")

    // App structure wrapped in Material3 Scaffold
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        bottomBar = {
            // Bottom navigation bar (visible on all screens except for Splash,SignUp, Log in, Balance, ForgotPassword and NewPasswordScreen)
            if (currentRoute !in bottomBarHiddenRoutes) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        // Inject NavGraph inside Scaffold with correct inner padding
        NavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
