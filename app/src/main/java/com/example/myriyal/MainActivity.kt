package com.example.myriyal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myriyal.navigation.AppNavigation
import com.example.myriyal.ui.theme.MyRiyalTheme
import dagger.hilt.android.AndroidEntryPoint

// MainActivity is the entry point of the app.
// It manually wires dependencies for both the Category and Record features,
// and passes them to the navigation graph for screen routing.
//
// Layers involved:
// - Data layer: Repositories (CategoryRepositoryImpl, RecordRepositoryImpl)
// - Domain layer: UseCases (for each feature)
// - Presentation layer: ViewModels (CategoryViewModel, RecordViewModel)
// - UI layer: Screens injected via NavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkTheme = isSystemInDarkTheme()
            val darkTheme = remember { mutableStateOf(isDarkTheme) }

            MyRiyalTheme(darkTheme = darkTheme.value) {
                AppNavigation(
                    darkTheme = darkTheme.value,
                    toggleTheme = { darkTheme.value = !darkTheme.value },
                ) // ← handles everything including nav and UI
            }
        }
    }
}