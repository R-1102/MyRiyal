package com.example.myriyal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myriyal.core.local.db.DatabaseProvider
import com.example.myriyal.screens.categories.data.repository.CategoryRepositoryImpl
import com.example.myriyal.screens.categories.presentation.CategoryScreen
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import com.example.myriyal.screens.authentication.presentation.screens.SignUpScreen
import com.example.myriyal.ui.theme.MyRiyalTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val dao = DatabaseProvider.getDatabase(this).categoryDao()
        val repo = CategoryRepositoryImpl(applicationContext)
        val viewModel = CategoryViewModel(repo)

        setContent {
            MyRiyalTheme {
                Scaffold(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    //SignUpScreen()
                }

                //Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CategoryScreen(viewModel = viewModel)
                //}
            }
        }
    }
}

