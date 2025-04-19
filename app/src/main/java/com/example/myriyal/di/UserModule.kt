package com.example.myriyal.di

import com.example.myriyal.screens.Profile.domain.repository.UserRepository
import com.example.myriyal.screens.Profile.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Hilt module to bind the UserRepository implementation to its interface.
// This makes the implementation injectable across the app where UserRepository is needed.
//
// Scope: Singleton — ensures only one instance is used during the app's lifecycle.

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    // Binds the implementation [UserRepositoryImpl] to the interface [UserRepository].
    // Allows Hilt to provide [UserRepository] wherever it's injected.
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}
