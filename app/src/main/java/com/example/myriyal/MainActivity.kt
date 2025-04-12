package com.example.myriyal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.myriyal.navigation.testing.AppNavGraph
import com.example.myriyal.screens.categories.data.repository.CategoryRepositoryImpl
import com.example.myriyal.screens.categories.domian.useCases.*
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel

import com.example.myriyal.screens.records.data.repository.RecordRepositoryImpl
import com.example.myriyal.screens.records.domain.useCases.*
import com.example.myriyal.screens.records.presentation.vmModels.RecordViewModel
import com.example.myriyal.ui.theme.MyRiyalTheme

// MainActivity is the entry point of the app.
// It manually wires dependencies for both the Category and Record features,
// and passes them to the navigation graph for screen routing.
//
// Layers involved:
// - Data layer: Repositories (CategoryRepositoryImpl, RecordRepositoryImpl)
// - Domain layer: UseCases (for each feature)
// - Presentation layer: ViewModels (CategoryViewModel, RecordViewModel)
// - UI layer: Screens injected via NavGraph
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // ----- CATEGORY Feature Wiring -----
        val categoryRepo = CategoryRepositoryImpl(applicationContext)
        val categoryUseCases = CategoryUseCases(
            insert = InsertCategoryUseCase(categoryRepo),
            update = UpdateCategoryUseCase(categoryRepo),
            softDelete = SoftDeleteCategoryUseCase(categoryRepo),
            delete = DeleteCategoryUseCase(categoryRepo),
            getAll = GetAllCategoriesUseCase(categoryRepo),
            seed = SeedPredefinedCategoriesUseCase(categoryRepo)
        )
        val categoryViewModel = CategoryViewModel(categoryUseCases)

        // ----- RECORD Feature Wiring -----
        val recordRepo = RecordRepositoryImpl(applicationContext)
        val recordUseCases = RecordUseCases(
            insert = InsertRecordUseCase(recordRepo),
            update = UpdateRecordUseCase(recordRepo),
            delete = DeleteRecordUseCase(recordRepo),
            getAllRecords = GetAllRecordsUseCase(recordRepo),
            getByCategory = GetRecordsByCategoryUseCase(recordRepo),
            getRecordById = GetRecordByIdUseCase(recordRepo)

        )
        val recordViewModel = RecordViewModel(recordUseCases)

        // ----- Compose Entry Point -----
        setContent {
            MyRiyalTheme {
                val navController = rememberNavController()

                // Navigation Graph handles switching between screens
                AppNavGraph(
                    navController = navController,
                    categoryViewModel = categoryViewModel,
                    recordViewModel = recordViewModel
                )
            }
        }
    }
}
