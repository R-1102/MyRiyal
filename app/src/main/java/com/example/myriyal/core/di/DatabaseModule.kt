package com.example.myriyal.core.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myriyal.core.db.MyRiyalDatabase
import com.example.myriyal.screens.categories.data.local.CategoryDao
import com.example.myriyal.screens.categories.data.local.TrackerDao
import com.example.myriyal.screens.profile.data.local.UserDao
import com.example.myriyal.screens.records.data.local.RecordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing Room database and its DAOs.
 * Ensures singleton instances for consistent and efficient DB access.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Room migration from version 1 to version 2.
     * Changes the primary key type of `userProfile` table from Int to String.
     */
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // 1. Create a new table with the desired schema (userId as TEXT)
            database.execSQL("""
                CREATE TABLE userProfile_new (
                    userId TEXT NOT NULL PRIMARY KEY,
                    userName TEXT NOT NULL,
                    balance REAL NOT NULL DEFAULT 0.0,
                    createdAt INTEGER NOT NULL,
                    updatedAt INTEGER NOT NULL
                )
            """)

            // 2. Copy data from old table to the new table, casting userId to TEXT
            database.execSQL("""
                INSERT INTO userProfile_new (userId, userName, balance, createdAt, updatedAt)
                SELECT CAST(userId AS TEXT), userName, balance, createdAt, updatedAt FROM userProfile
            """)

            // 3. Remove the old table
            database.execSQL("DROP TABLE userProfile")

            // 4. Rename the new table to the original table name
            database.execSQL("ALTER TABLE userProfile_new RENAME TO userProfile")
        }
    }

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE category ADD COLUMN isSync INTEGER NOT NULL DEFAULT 0")
            database.execSQL("ALTER TABLE record ADD COLUMN isSync INTEGER NOT NULL DEFAULT 0")
            database.execSQL("ALTER TABLE trackers ADD COLUMN isSync INTEGER NOT NULL DEFAULT 0")
            database.execSQL("ALTER TABLE userProfile ADD COLUMN isSync INTEGER NOT NULL DEFAULT 0")
        }
    }

    /**
     * Provides the Room database instance with the migration applied.
     */
    @Provides
    @Singleton
    fun provideDatabase(app: Application): MyRiyalDatabase {
        return Room.databaseBuilder(
            app,
            MyRiyalDatabase::class.java,
            "myRiyalDB"
        ).addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()
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
