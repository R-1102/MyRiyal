package com.example.myriyal.utils

import android.content.Context
import com.example.myriyal.screens.categories.data.repository.CategoryRepositoryImpl
import com.example.myriyal.screens.categories.domian.useCases.*
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel

/**
 * Provides an instance of [CategoryViewModel] without relying on dependency injection.
 *
 * This function manually wires:
 * - The data layer: [CategoryRepositoryImpl]
 * - The domain layer: [CategoryUseCases] (a container for all category-related use cases)
 * - The presentation layer: [CategoryViewModel]
 *
 * Purpose:
 * Used in the application entry point (e.g., MyRiyalApp) to supply a fully wired ViewModel
 * to the UI and navigation system when dependency injection tools like Hilt are not used.
 *
 * Architecture Flow:
 * UI → ViewModel → UseCases → Repository → Room DAO
 *
 * @param context Application context needed to initialize the Room database and DAO.
 * @return A ready-to-use instance of [CategoryViewModel]
 */
fun provideCategoryViewModel(context: Context): CategoryViewModel {
    // Initialize repository with access to Room database
    val repo = CategoryRepositoryImpl(context)

    // Bundle all business logic use cases related to Category into one container
    val useCases = CategoryUseCases(
        insert = InsertCategoryUseCase(repo),
        update = UpdateCategoryUseCase(repo),
        softDelete = SoftDeleteCategoryUseCase(repo),
        delete = DeleteCategoryUseCase(repo),
        getAll = GetAllCategoriesUseCase(repo),
        seed = SeedPredefinedCategoriesUseCase(repo)
    )

    // Return a ViewModel configured with the use case container
    return CategoryViewModel(useCases)
}
