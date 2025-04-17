package com.example.myriyal

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myriyal.screens.categories.presentation.screens.AddCategory
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
            MyRiyalTheme {
//                AppNavigation() // ← handles everything including nav and UI
//                val categoryViewModel = provideCategoryViewModel(this)
//                AddCategory()

            }
        }
    }
}
