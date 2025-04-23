package com.example.myriyal.di

import com.example.myriyal.screens.authentication.data.repositories_imp.NotificationRepositoryImpl
import com.example.myriyal.screens.authentication.domain.repository.NotificationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindNotificationRepository(
        impl: NotificationRepositoryImpl
    ): NotificationRepository
}
