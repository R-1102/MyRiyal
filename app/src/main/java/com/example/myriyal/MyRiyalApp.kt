package com.example.myriyal


import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp



import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.myriyal.navigation.NavGraph
import com.example.myriyal.screens.mainScreen.BottomNavigationBar
import com.example.myriyal.utils.provideCategoryViewModel
import com.example.myriyal.utils.provideRecordViewModel

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
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyRiyalApp() {
    // Context reference for providing dependencies
    val context = LocalContext.current

    // ViewModel providers (encapsulated wiring logic for better separation of concerns)
    val categoryViewModel = provideCategoryViewModel(context)
    val recordViewModel = provideRecordViewModel(context)

    // Navigation controller for handling screen routing
    val navController = rememberNavController()

    // App structure wrapped in Material3 Scaffold
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        bottomBar = {
            // Bottom navigation bar (visible on all screens)
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        // Inject NavGraph inside Scaffold with correct inner padding
        NavGraph(
            navController = navController,
            categoryViewModel = categoryViewModel,
            recordViewModel = recordViewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
@HiltAndroidApp
class MyRiyalApp : Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
