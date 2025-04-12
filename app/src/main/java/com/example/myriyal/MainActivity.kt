package com.example.myriyal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myriyal.screens.categories.data.repository.CategoryRepositoryImpl
import com.example.myriyal.screens.categories.domian.useCases.*
import com.example.myriyal.screens.categories.presentation.CategoryScreen
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import com.example.myriyal.ui.theme.MyRiyalTheme

// MainActivity is the entry point of the app.
// It manually wires the dependencies (repository, use cases, view model) and launches the UI.
//
// Architecture Flow:
// - MainActivity builds the CategoryRepositoryImpl (data layer)
// - Passes the repository to all Category use cases (domain layer)
// - Combines use cases into a single container (CategoryUseCases)
// - Injects the use cases into CategoryViewModel (presentation layer)
// - ViewModel is passed to CategoryScreen (UI layer)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables full edge-to-edge content rendering
        enableEdgeToEdge()

        // Instantiate the repository that connects to the DAO
        val repo = CategoryRepositoryImpl(applicationContext)

        // Bundle all use cases related to the Category feature
        val useCases = CategoryUseCases(
            insert = InsertCategoryUseCase(repo),
            update = UpdateCategoryUseCase(repo),
            softDelete = SoftDeleteCategoryUseCase(repo),
            delete = DeleteCategoryUseCase(repo),
            getAll = GetAllCategoriesUseCase(repo),
            seed = SeedPredefinedCategoriesUseCase(repo)
        )

        // ViewModel acts as the bridge between the UI and domain logic (use cases)
        val viewModel = CategoryViewModel(useCases)

        // COMPOSE UI ENTRY POINT
        setContent {
            MyRiyalTheme {
                // CategoryScreen is the main UI screen for managing categories
                // It receives the ViewModel, observes state, and triggers logic
                CategoryScreen(viewModel = viewModel)
            }
        }
    }
}
