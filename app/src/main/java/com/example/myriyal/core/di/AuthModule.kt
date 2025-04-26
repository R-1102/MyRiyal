package com.example.myriyal.core.di

import androidx.security.crypto.EncryptedSharedPreferences
import com.example.myriyal.screens.authentication.data.dataSources.AuthDataSource
import com.example.myriyal.screens.authentication.data.repository.AuthRepositoryImp
import com.example.myriyal.screens.authentication.domain.repository.AuthRepository
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
    fun provideAuthDataSource(auth: FirebaseAuth,encryptedSharedPreferences: EncryptedSharedPreferences): AuthDataSource {
        return AuthDataSource(auth,encryptedSharedPreferences)
    }

    @Provides
    @Singleton
    fun provideAuthRepo(dataSource: AuthDataSource): AuthRepository {
        return AuthRepositoryImp(dataSource)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(repo: AuthRepository): SignUpUseCase {
        return SignUpUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideLogInUseCase(repo: AuthRepository):LogInUseCase{
        return LogInUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideLogOutUseCase(repo: AuthRepository): LogOutUseCase {
        return LogOutUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideForgotPasswordUseCase(repo: AuthRepository):ForgotPasswordUseCase {
        return ForgotPasswordUseCase(repo)
    }

}
