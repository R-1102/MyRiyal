package com.example.myriyal.di

import android.content.Context
import com.example.myriyal.screens.categories.data.local.CategoryDao
import com.example.myriyal.core.utils.ConnectivityStatus
import com.example.myriyal.screens.categories.data.api.CategoryApiService
import com.example.myriyal.screens.categories.data.dataSources.CategoryDataSource
import com.example.myriyal.screens.categories.data.dataSources.LocalCategoryDataSource
import com.example.myriyal.screens.categories.data.dataSources.RemoteCategoryDataSource
import com.example.myriyal.screens.categories.data.repository.CategoryRepositoryImpl
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    @Named("local")
    fun provideLocalCategoryDataSource(categoryDao: CategoryDao): CategoryDataSource {
        return LocalCategoryDataSource(categoryDao)
    }

    @Provides
    @Singleton
    fun provideCategoryApiService(retrofit: Retrofit): CategoryApiService =
        retrofit.create(CategoryApiService::class.java)

    @Provides
    @Singleton
    @Named("remote")
    fun provideRemoteCategoryRepository(categoryApiService: CategoryApiService): CategoryDataSource {//will take the api
        return RemoteCategoryDataSource(categoryApiService)
    }

    @Provides
    @Singleton
    fun provideConnectivityStatus(@ApplicationContext context: Context): ConnectivityStatus {
        return ConnectivityStatus(context)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(
        @Named("local") localCategoryDataSource: CategoryDataSource,
        @Named("remote") remoteCategoryDataSource: CategoryDataSource,
        connectivityStatus: ConnectivityStatus,
        dao : CategoryDao
    ): CategoryRepository {
        return CategoryRepositoryImpl(
            localCategoryDataSource,
            remoteCategoryDataSource,
            connectivityStatus,
            dao
        )
    }
}
