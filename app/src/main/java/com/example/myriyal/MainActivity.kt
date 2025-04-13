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

import androidx.navigation.compose.rememberNavController
import com.example.myriyal.navigation.testing.AppNavGraph
import com.example.myriyal.screens.categories.data.repository.CategoryRepositoryImpl
import com.example.myriyal.screens.categories.domian.useCases.*
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel

import com.example.myriyal.screens.records.data.repository.RecordRepositoryImpl
import com.example.myriyal.screens.records.domain.useCases.*
import com.example.myriyal.screens.records.presentation.vmModels.RecordViewModel

import com.example.myriyal.ui.theme.MyRiyalTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyRiyalTheme {
               // MyRiyalApp() // ← only this one line for full app setup


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
