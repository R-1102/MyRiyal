package com.example.myriyal.di

import com.example.myriyal.screens.authentication.data.repository.BaseAuthenticator
import com.example.myriyal.screens.authentication.data.repository.FirebaseAuthenticator
import com.example.myriyal.screens.authentication.domain.repository.BaseAuthRepository
import com.example.myriyal.screens.authentication.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepository
    ): BaseAuthRepository

    @Binds
    @Singleton
    abstract fun bindBaseAuthenticator(
        firebaseAuthenticator: FirebaseAuthenticator
    ): BaseAuthenticator
}
