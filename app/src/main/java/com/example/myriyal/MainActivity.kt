package com.example.myriyal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge


import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myriyal.core.local.db.DatabaseProvider
import com.example.myriyal.navigation.AppNavGraph

import androidx.navigation.compose.rememberNavController
import com.example.myriyal.navigation.testing.AppNavGraph
import com.example.myriyal.screens.categories.data.repository.CategoryRepositoryImpl
import com.example.myriyal.screens.categories.domian.useCases.*
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel

import com.example.myriyal.screens.records.data.repository.RecordRepositoryImpl
import com.example.myriyal.screens.records.domain.useCases.*
import com.example.myriyal.screens.records.presentation.vmModels.RecordViewModel

import com.example.myriyal.ui.theme.MyRiyalTheme
import dagger.hilt.android.HiltAndroidApp

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
                AppNavigation() // ← handles everything including nav and UI
            }
        }
    }
}
