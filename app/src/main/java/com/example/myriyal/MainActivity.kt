package com.example.myriyal

import android.annotation.SuppressLint
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
import com.example.myriyal.screens.categories.data.repository.CategoryRepositoryImpl
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel

import com.example.myriyal.ui.theme.MyRiyalTheme

// MainActivity is the entry point of the app
// It bootstraps the database, repository, and manually wires the ViewModel
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()


        // 2. Create repository implementation (uses the DAO internally)
        val repo = CategoryRepositoryImpl(applicationContext)

        // 3. Create ViewModel and pass in the repository
        //    Normally done using ViewModelProvider or Hilt, but manual for now
        val viewModel = CategoryViewModel(repo)

        // COMPOSE UI ENTRY POINT
        // Starts the Jetpack Compose rendering
        val dao = DatabaseProvider.getDatabase(this).categoryDao()

        setContent {
            MyRiyalTheme {
                val navController = rememberNavController()

                // UI starts here. CategoryScreen is the first visible screen.
                //CategoryScreen(viewModel = viewModel)
                Scaffold(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    AppNavGraph(navController = navController)
                }

            }
        }
    }
}


//