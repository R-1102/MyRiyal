package com.example.myriyal.screens.categories.domian.useCases

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository

// Use Case: UpdateCategoryUseCase
//
// Belongs to the domain layer. Encapsulates the business logic for updating an existing category.
// This use case allows the ViewModel (or any presentation logic) to update a category
// without directly accessing the repository, promoting separation of concerns.
//
// Called from: CategoryViewModel.update()
// Calls into: CategoryRepository.updateCategory()

class UpdateCategoryUseCase(
    private val repository: CategoryRepository
) {
    // Executes the use case
    // 'invoke' allows this class to be called like a function: updateUseCase(category)
    suspend operator fun invoke(category: CategoryEntity) {
        repository.updateCategory(category)
    }
}
