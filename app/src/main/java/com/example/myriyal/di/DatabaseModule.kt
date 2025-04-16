package com.example.myriyal.di

import android.app.Application
import androidx.room.Room
import com.example.myriyal.core.local.dao.*
import com.example.myriyal.core.local.db.MyRiyalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides Room Database and DAOs to the app using Hilt Dependency Injection.
 *
 * Scope: Singleton (lives as long as the app)
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides a singleton instance of the Room database.
     */
    @Provides
    @Singleton
    fun provideDatabase(app: Application): MyRiyalDatabase {
        return Room.databaseBuilder(
            app,
            MyRiyalDatabase::class.java,
            "myRiyalDB"
        ).build()
    }

    /**
     * Provides DAO for Category operations.
     */
    @Provides
    fun provideCategoryDao(db: MyRiyalDatabase): CategoryDao = db.categoryDao()

    /**
     * Provides DAO for Record operations.
     */
    @Provides
    fun provideRecordDao(db: MyRiyalDatabase): RecordDao = db.recordDao()

    /**
     * Provides DAO for User operations.
     */
    @Provides
    fun provideUserDao(db: MyRiyalDatabase): UserDao = db.userDao()

    /**
     * Provides DAO for Tracker operations.
     */
    @Provides
    fun provideTrackerDao(db: MyRiyalDatabase): TrackerDao = db.trackerDao()
}
