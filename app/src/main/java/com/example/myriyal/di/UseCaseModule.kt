package com.example.myriyal.di

import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import com.example.myriyal.screens.categories.domian.repository.TrackerRepository
import com.example.myriyal.screens.categories.domian.useCases.CategoryUseCases
import com.example.myriyal.screens.categories.domian.useCases.DeleteCategoryUseCase
import com.example.myriyal.screens.categories.domian.useCases.GetAllCategoriesUseCase
import com.example.myriyal.screens.categories.domian.useCases.GetSpentAmountForCategoryUseCase
import com.example.myriyal.screens.categories.domian.useCases.GetTrackerByCategoryIdUseCase
import com.example.myriyal.screens.categories.domian.useCases.InsertCategoryUseCase
import com.example.myriyal.screens.categories.domian.useCases.InsertTrackerUseCase
import com.example.myriyal.screens.categories.domian.useCases.InsertWithTrackerUseCase
import com.example.myriyal.screens.categories.domian.useCases.SeedPredefinedCategoriesUseCase
import com.example.myriyal.screens.categories.domian.useCases.SoftDeleteCategoryUseCase
import com.example.myriyal.screens.categories.domian.useCases.UpdateCategoryUseCase
import com.example.myriyal.screens.records.domain.repository.RecordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.firstOrNull
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
        categoryRepository: CategoryRepository,
        trackerRepository: TrackerRepository,
        recordRepository : RecordRepository
    ): CategoryUseCases {
        return CategoryUseCases(
            insert = InsertCategoryUseCase(categoryRepository),
            update = UpdateCategoryUseCase(categoryRepository),
            softDelete = SoftDeleteCategoryUseCase(categoryRepository),
            delete = DeleteCategoryUseCase(categoryRepository),
            getAll = GetAllCategoriesUseCase(categoryRepository),
            seed = SeedPredefinedCategoriesUseCase(categoryRepository),
            insertWithTracker = InsertWithTrackerUseCase(categoryRepository, trackerRepository),
            getSpentAmount = GetSpentAmountForCategoryUseCase(recordRepository),
            insertTracker = { tracker -> trackerRepository.insertTracker(tracker) },
            updateTracker = { tracker -> trackerRepository.updateTracker(tracker) },
            getTrackerByCategoryId = { categoryId ->
                trackerRepository.getTrackerByCategoryId(categoryId).firstOrNull()
            }


        )
    }

    /**
     * Provides [InsertTrackerUseCase] as a singleton.
     *
     * Used independently if tracker needs to be inserted separately from Category creation.
     */
    @Provides
    @Singleton
    fun provideInsertTrackerUseCase(repository: TrackerRepository): InsertTrackerUseCase {
        return InsertTrackerUseCase(repository)
    }

    /**
     * Provides [GetTrackerByCategoryIdUseCase] as a singleton.
     *
     * Used when fetching a tracker during category edit flow or budget view.
     */
    @Provides
    @Singleton
    fun provideGetTrackerByCategoryIdUseCase(repository: TrackerRepository): GetTrackerByCategoryIdUseCase {
        return GetTrackerByCategoryIdUseCase(repository)
    }
}