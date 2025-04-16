package com.example.myriyal.di

import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import com.example.myriyal.screens.categories.domian.useCases.CategoryUseCases
import com.example.myriyal.screens.categories.domian.useCases.DeleteCategoryUseCase
import com.example.myriyal.screens.categories.domian.useCases.GetAllCategoriesUseCase
import com.example.myriyal.screens.categories.domian.useCases.InsertCategoryUseCase
import com.example.myriyal.screens.categories.domian.useCases.SeedPredefinedCategoriesUseCase
import com.example.myriyal.screens.categories.domian.useCases.SoftDeleteCategoryUseCase
import com.example.myriyal.screens.categories.domian.useCases.UpdateCategoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides all use cases as a single object for Hilt to inject into ViewModel.
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideCategoryUseCases(
        repository: CategoryRepository
    ): CategoryUseCases {
        return CategoryUseCases(
            insert = InsertCategoryUseCase(repository),
            update = UpdateCategoryUseCase(repository),
            softDelete = SoftDeleteCategoryUseCase(repository),
            delete = DeleteCategoryUseCase(repository),
            getAll = GetAllCategoriesUseCase(repository),
            seed = SeedPredefinedCategoriesUseCase(repository)
        )
    }
}
