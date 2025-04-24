package com.example.myriyal.screens.categories.domian.useCases

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoriesUseCase(
    private val repository: CategoryRepository
) {
    operator fun invoke(): Flow<List<CategoryEntity>> {
        return repository.getAllCategories()
    }
}