package com.example.myriyal.di

import androidx.security.crypto.EncryptedSharedPreferences
import com.example.myriyal.screens.authentication.data.data_sources.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        encryptedPrefs: EncryptedSharedPreferences
    ): AuthInterceptor {
        return AuthInterceptor(encryptedPrefs)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://project-1-admissions3.replit.app/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
