package com.example.myriyal.core.di

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Hilt module that provides secure, encrypted shared preferences
@Module
@InstallIn(SingletonComponent::class) // Makes this module available for the entire application lifecycle
object EncryptedSharedPreferencesModule {

    /**
     * Provides an instance of EncryptedSharedPreferences that is:
     * - Encrypted using AES-256 for both keys and values
     * - Backed by Android's Keystore system for secure key storage
     * - Available application-wide as a singleton dependency
     *
     * Fetching:
     * - The method receives the application context injected by Hilt
     * - It retrieves or creates a secure master key using Android's Keystore
     *
     * Sending:
     * - The created EncryptedSharedPreferences instance is returned
     * - Hilt will inject this instance into any class that declares it as a dependency
     */
    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(
        @ApplicationContext context: Context // Application context provided by Hilt
    ): EncryptedSharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC) // Secure master key from Android Keystore

        return EncryptedSharedPreferences.create(
            "secure_prefs",               // Name of the encrypted SharedPreferences file
            masterKeyAlias,               // Key used to encrypt/decrypt data
            context,                      // Context used to access app's private file storage
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,   // Encryption scheme for keys
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // Encryption scheme for values
        ) as EncryptedSharedPreferences // Provided instance injected by Hilt into dependent classes
    }
}
