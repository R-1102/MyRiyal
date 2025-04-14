package com.example.myriyal

import AddCategory
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import com.example.myriyal.ui.theme.MyRiyalTheme
import dagger.hilt.android.HiltAndroidApp
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
import com.example.myriyal.utils.provideCategoryViewModel
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyRiyalTheme {
                MyRiyalApp() // ← handles everything including nav and UI

            }
        }
    }
}
