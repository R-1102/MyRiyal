package com.example.myriyal.di

import com.example.myriyal.core.local.dao.CategoryDao
import com.example.myriyal.screens.categories.data.repository.CategoryRepositoryImpl
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides the CategoryRepository dependency.
 * Assumes CategoryDao is already provided via DatabaseModule.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Provides the implementation of the CategoryRepository.
     */
    @Provides
    @Singleton
    fun provideCategoryRepository(categoryDao: CategoryDao): CategoryRepository {
        return CategoryRepositoryImpl(categoryDao)
    }
}
