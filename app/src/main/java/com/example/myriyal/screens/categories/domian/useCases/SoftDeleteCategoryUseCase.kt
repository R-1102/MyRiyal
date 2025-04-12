package com.example.myriyal.screens.categories.domian.useCases

import com.example.myriyal.screens.categories.domian.repository.CategoryRepository

// Use case for soft-deleting a category by marking it as INACTIVE.
// Belongs to the domain layer. Represents business logic related to deactivation.
//
// Called from: CategoryViewModel → viewModelScope.launch { useCases.softDelete(categoryId) }
// Sends data to: CategoryRepository.softDeleteCategory()
// Final destination: DAO updates the `status` field of the category (without deleting the row)

class SoftDeleteCategoryUseCase(
    private val repository: CategoryRepository
) {
    // Invoked with the category ID to soft delete
    suspend operator fun invoke(categoryId: Int) {
        repository.softDeleteCategory(categoryId)
    }
}
