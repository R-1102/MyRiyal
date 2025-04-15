package com.example.myriyal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myriyal.screens.categories.domian.useCases.*
import com.example.myriyal.screens.records.domain.useCases.*
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

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyRiyalTheme {
                AppNavigation() // ← only this one line for full app setup


//                 val navController = rememberNavController()


//                 // UI starts here. CategoryScreen is the first visible screen.
//                 //CategoryScreen(viewModel = viewModel)
//                  Scaffold(
//                      modifier = Modifier
//                          .background(color = MaterialTheme.colorScheme.background)
//                  ) {
//                      AppNavGraph(navController = navController)
//                  }


            }
        }
    }
}
