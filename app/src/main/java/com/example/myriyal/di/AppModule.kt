package com.example.myriyal.di

import com.example.myriyal.screens.authentication.data.data_sources.AuthDataSource
import com.example.myriyal.screens.authentication.data.repositories_imp.AuthRepoImp
import com.example.myriyal.screens.authentication.domain.repository.AuthRepo
import com.example.myriyal.screens.authentication.domain.useCases.SignUpUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthDataSource(auth: FirebaseAuth): AuthDataSource {
        return AuthDataSource(auth)
    }

    @Provides
    @Singleton
    fun provideAuthRepo(dataSource: AuthDataSource): AuthRepo {
        return AuthRepoImp(dataSource)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(repo: AuthRepo): SignUpUseCase {
        return SignUpUseCase(repo)
    }
}
