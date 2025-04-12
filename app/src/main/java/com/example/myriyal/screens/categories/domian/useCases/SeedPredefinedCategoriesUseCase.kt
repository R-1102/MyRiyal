package com.example.myriyal.screens.categories.domian.useCases

import com.example.myriyal.screens.categories.domian.repository.CategoryRepository

// Use case for seeding the database with predefined categories.
// Called during the app launch (typically once) to initialize default values.
//
// Called from: CategoryViewModel → viewModelScope.launch { useCases.seed() }
// Sends data to: CategoryRepository.seedPredefinedCategories()
// Final destination: DAO inserts predefined CategoryEntity rows into the database

class SeedPredefinedCategoriesUseCase(
    private val repository: CategoryRepository
) {
    // Executes the seeding logic
    suspend operator fun invoke() {
        repository.seedPredefinedCategories()
    }
}
