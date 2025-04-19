package com.example.myriyal.di

import com.example.myriyal.screens.authentication.data.data_sources.AuthDataSource
import com.example.myriyal.screens.authentication.data.repositories_imp.AuthRepoImp
import com.example.myriyal.screens.authentication.domain.repository.AuthRepo
import com.example.myriyal.screens.authentication.domain.useCases.ForgotPasswordUseCase
import com.example.myriyal.screens.authentication.domain.useCases.LogInUseCase
import com.example.myriyal.screens.authentication.domain.useCases.LogOutUseCase
import com.example.myriyal.screens.authentication.domain.useCases.SignUpUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

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

    @Provides
    @Singleton
    fun provideLogInUseCase(repo: AuthRepo):LogInUseCase{
        return LogInUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideLogOutUseCase(repo: AuthRepo): LogOutUseCase {
        return LogOutUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideForgotPasswordUseCase(repo: AuthRepo):ForgotPasswordUseCase {
        return ForgotPasswordUseCase(repo)
    }

}
