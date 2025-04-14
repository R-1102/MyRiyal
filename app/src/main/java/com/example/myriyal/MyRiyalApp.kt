package com.example.myriyal

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.myriyal.screens.categories.data.repository.CategoryRepositoryImpl
import com.example.myriyal.screens.categories.domian.useCases.CategoryUseCases
import com.example.myriyal.screens.categories.domian.useCases.DeleteCategoryUseCase
import com.example.myriyal.screens.categories.domian.useCases.GetAllCategoriesUseCase
import com.example.myriyal.screens.categories.domian.useCases.InsertCategoryUseCase
import com.example.myriyal.screens.categories.domian.useCases.SeedPredefinedCategoriesUseCase
import com.example.myriyal.screens.categories.domian.useCases.SoftDeleteCategoryUseCase
import com.example.myriyal.screens.categories.domian.useCases.UpdateCategoryUseCase
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import com.example.myriyal.screens.mainScreen.MainScreen
import com.example.myriyal.screens.records.data.repository.RecordRepositoryImpl
import com.example.myriyal.screens.records.domain.useCases.DeleteRecordUseCase
import com.example.myriyal.screens.records.domain.useCases.GetAllRecordsUseCase
import com.example.myriyal.screens.records.domain.useCases.GetRecordByIdUseCase
import com.example.myriyal.screens.records.domain.useCases.GetRecordsByCategoryUseCase
import com.example.myriyal.screens.records.domain.useCases.InsertRecordUseCase
import com.example.myriyal.screens.records.domain.useCases.RecordUseCases
import com.example.myriyal.screens.records.domain.useCases.UpdateRecordUseCase
import com.example.myriyal.screens.records.presentation.vmModels.RecordViewModel

/**
 * Root Composable for launching the MyRiyal app.
 * This function sets up all required dependencies for features and passes them into the UI layer.
 *
 * This approach follows Clean Architecture principles with clear separation of layers:
 * - Data Layer: Repository implementations (connect to DAOs)
 * - Domain Layer: Use cases (contain business logic)
 * - Presentation Layer: ViewModels (connect UI to domain)
 * - UI Layer: Composables (MainScreen)
 */
@Composable
fun MyRiyalApp() {
    // Provides application context used by database + repository
    val context = LocalContext.current

    // ------------------------------------
    // CATEGORY FEATURE SETUP
    // ------------------------------------

    // Repository implementation (data layer)
    // Fetches/saves category data from/to Room DB
    val categoryRepo = CategoryRepositoryImpl(context)

    // UseCases (domain layer) for category logic
    // Called by the ViewModel to perform actions
    val categoryUseCases = CategoryUseCases(
        insert = InsertCategoryUseCase(categoryRepo),
        update = UpdateCategoryUseCase(categoryRepo),
        softDelete = SoftDeleteCategoryUseCase(categoryRepo),
        delete = DeleteCategoryUseCase(categoryRepo),
        getAll = GetAllCategoriesUseCase(categoryRepo),
        seed = SeedPredefinedCategoriesUseCase(categoryRepo)
    )

    // ViewModel (presentation layer)
    // Exposes state and handles user actions from CategoryScreen
    val categoryViewModel = CategoryViewModel(categoryUseCases)

    // ------------------------------------
    // RECORD FEATURE SETUP
    // ------------------------------------

    // Repository implementation for records (data layer)
    val recordRepo = RecordRepositoryImpl(context)

    // UseCases (domain layer) for record logic
    val recordUseCases = RecordUseCases(
        insert = InsertRecordUseCase(recordRepo),
        update = UpdateRecordUseCase(recordRepo),
        delete = DeleteRecordUseCase(recordRepo),
        getAllRecords = GetAllRecordsUseCase(recordRepo),
        getByCategory = GetRecordsByCategoryUseCase(recordRepo),
        getRecordById = GetRecordByIdUseCase(recordRepo)
    )

    // ViewModel (presentation layer) for record screen
    val recordViewModel = RecordViewModel(recordUseCases)

    // ------------------------------------
    // NAVIGATION
    // ------------------------------------

    // Compose Navigation controller to manage screen routing
    val navController = rememberNavController()

    // Inject ViewModels into the MainScreen (UI layer)
    // MainScreen hosts the navigation graph and UI content
    MainScreen(
        navController = navController,
        categoryViewModel = categoryViewModel,
        recordViewModel = recordViewModel
    )
}

