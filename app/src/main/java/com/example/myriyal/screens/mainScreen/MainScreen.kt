package com.example.myriyal.screens.mainScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

/**
 * MainScreen is the root composable for handling navigation between top-level screens.
 *
 * It receives ViewModels (presentation layer) that already contain business logic from the domain layer.
 *
 * Data flow:
 * - Receives: CategoryViewModel, RecordViewModel
 * - Sends: UI updates and user actions to the corresponding screens (CategoryScreen, RecordScreen)
 * - Fetches: UI state from the ViewModels via StateFlows
 */
//@Composable
//fun MainScreen(
//    navController: NavHostController,
//    categoryViewModel: CategoryViewModel,
//    recordViewModel: RecordViewModel
//) {
//    Scaffold(
//        // BottomNavigationBar allows switching between main feature screens
//        bottomBar = {
//            BottomNavigationBar(navController = navController)
//        }
//    ) { innerPadding ->
//
//        // Navigation graph that defines which screen to show based on route
//        NavHost(
//            navController = navController,
//            startDestination = "category_screen", // Default screen on app launch
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            // Category feature screen
//            composable("category_screen") {
//                CategoryScreen(viewModel = categoryViewModel)
//                // UI reads data from: CategoryViewModel
//                // UI sends actions to: CategoryViewModel.insert/update/delete
//            }
//
//            // Record feature screen
//            composable("record_screen") {
//                RecordScreen(
//                    viewModel = recordViewModel,
//                    categoryViewModel = categoryViewModel
//                )
//                // UI reads: records from RecordViewModel, categories from CategoryViewModel
//                // UI sends actions to: RecordViewModel.insert/update/delete
//            }
//        }
//    }
//}
//
///**
// * BottomNavigationBar displays the bottom nav with tabs to switch between
// * the two main features: Categories and Records.
// */
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("category_screen") },
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Categories") },
            label = { Text("Categories") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("record_screen") },
            icon = { Icon(Icons.Default.Receipt, contentDescription = "Records") },
            label = { Text("Records") }
        )
    }
}
