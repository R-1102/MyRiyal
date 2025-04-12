package com.example.myriyal.screens.categories.domian.useCases

// Aggregates all category-related use cases into a single object.
// This wrapper is passed to the ViewModel so it can access each individual use case.
//
// Called from: MainActivity (manual dependency injection)
// Used in: CategoryViewModel to perform actions like insert, update, delete, etc.

data class CategoryUseCases(
    val insert: InsertCategoryUseCase,                // For inserting new categories
    val update: UpdateCategoryUseCase,                // For updating existing categories
    val softDelete: SoftDeleteCategoryUseCase,        // For marking a category as INACTIVE
    val delete: DeleteCategoryUseCase,                // For permanently deleting a category
    val getAll: GetAllCategoriesUseCase,              // For retrieving all categories from DB
    val seed: SeedPredefinedCategoriesUseCase         // For inserting predefined categories at startup
)
