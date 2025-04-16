package com.example.myriyal.screens.categories.domian.useCases

//Aggregates all category-related use cases into a single object.
//Injected into the ViewModel via Hilt.
//
//This makes it easier to pass one object instead of many use cases.


data class CategoryUseCases(
    val insert: InsertCategoryUseCase,                // For inserting new categories
    val update: UpdateCategoryUseCase,                // For updating existing categories
    val softDelete: SoftDeleteCategoryUseCase,        // For marking a category as INACTIVE
    val delete: DeleteCategoryUseCase,                // For permanently deleting a category
    val getAll: GetAllCategoriesUseCase,              // For retrieving all categories from DB
    val seed: SeedPredefinedCategoriesUseCase         // For inserting predefined categories at startup
)
