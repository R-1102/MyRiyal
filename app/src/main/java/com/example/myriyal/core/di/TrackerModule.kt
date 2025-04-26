package com.example.myriyal.core.di

import com.example.myriyal.screens.categories.data.repository.TrackerRepositoryImpl
import com.example.myriyal.screens.categories.domian.repository.TrackerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger-Hilt module for TrackerRepository dependency injection.
 *
 * Purpose:
 * - Provides a singleton instance of [TrackerRepository] by binding [TrackerRepositoryImpl].
 * - Used throughout the app wherever TrackerRepository is required.
 *
 * Data flow:
 * - Called by: Hilt when injecting TrackerRepository into ViewModels or UseCases.
 * - Binds implementation: TrackerRepositoryImpl → TrackerRepository
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class TrackerModule {

    /**
     * Binds TrackerRepositoryImpl as the implementation for TrackerRepository interface.
     *
     * - Provided as a singleton across the app.
     */
    @Binds
    @Singleton
    abstract fun bindTrackerRepository(
        impl: TrackerRepositoryImpl
    ): TrackerRepository

}
