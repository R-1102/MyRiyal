package com.example.myriyal.screens.categories.domian.useCases

import com.example.myriyal.core.local.entities.TrackerEntity

/**
 * Aggregates all category-related use cases into a single object.
 * This is injected into the ViewModel via Hilt to reduce constructor clutter and improve modularity.
 *
 * Purpose:
 * - Groups all business logic related to categories and trackers.
 * - Provides a clear structure for all supported operations.
 * - Improves testability and maintainability.
 */
data class CategoryUseCases(
    val insert: InsertCategoryUseCase,                     // Inserts a new category into the database
    val update: UpdateCategoryUseCase,                     // Updates an existing category
    val softDelete: SoftDeleteCategoryUseCase,             // Soft-deletes a category (marks as INACTIVE)
    val delete: DeleteCategoryUseCase,                     // Permanently deletes a category
    val getAll: GetAllCategoriesUseCase,                   // Retrieves all categories as a Flow
    val seed: SeedPredefinedCategoriesUseCase,             // Seeds predefined categories on first launch
    val insertWithTracker: InsertWithTrackerUseCase,       // Inserts a category along with an optional tracker
    val getSpentAmount: GetSpentAmountForCategoryUseCase,  // Calculates total spent amount for a category
    val insertTracker: suspend (TrackerEntity) -> Unit,    // Inserts a new tracker entity (inline function)
    val updateTracker: suspend (TrackerEntity) -> Unit,    // Updates an existing tracker entity (inline function)
    val getTrackerByCategoryId: suspend (Int) -> TrackerEntity?, // Retrieves tracker info by category ID
    val searchCategoryByName: SearchCategoryByNameUseCase
)
