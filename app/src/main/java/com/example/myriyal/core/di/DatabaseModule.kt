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
     * Migration from version 1 to 2:
     * Changes the primary key type of `userProfile` table from Int to String.
     */
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("""
                CREATE TABLE userProfile_new (
                    userId TEXT NOT NULL PRIMARY KEY,
                    userName TEXT NOT NULL,
                    balance REAL NOT NULL DEFAULT 0.0,
                    createdAt INTEGER NOT NULL,
                    updatedAt INTEGER NOT NULL
                )
            """)
            database.execSQL("""
                INSERT INTO userProfile_new (userId, userName, balance, createdAt, updatedAt)
                SELECT CAST(userId AS TEXT), userName, balance, createdAt, updatedAt FROM userProfile
            """)
            database.execSQL("DROP TABLE userProfile")
            database.execSQL("ALTER TABLE userProfile_new RENAME TO userProfile")
        }
    }

    /**
     * Migration from version 2 to 3:
     * Adds `isSync` column to category, record, trackers, and userProfile tables.
     */
    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE category ADD COLUMN isSync INTEGER NOT NULL DEFAULT 0")
            database.execSQL("ALTER TABLE record ADD COLUMN isSync INTEGER NOT NULL DEFAULT 0")
            database.execSQL("ALTER TABLE trackers ADD COLUMN isSync INTEGER NOT NULL DEFAULT 0")
            database.execSQL("ALTER TABLE userProfile ADD COLUMN isSync INTEGER NOT NULL DEFAULT 0")
        }
    }

    /**
     * Migration from version 3 to 4:
     * Rebuilds the `category` table with correct defaults for `status` and `type` columns.
     */
    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("""
                CREATE TABLE category_new (
                    categoryId INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    color TEXT NOT NULL,
                    icon TEXT,
                    type TEXT NOT NULL DEFAULT 'EXPENSE',
                    status TEXT NOT NULL DEFAULT 'ACTIVE',
                    isPredefined INTEGER NOT NULL DEFAULT 1,
                    createdAt INTEGER NOT NULL,
                    updatedAt INTEGER NOT NULL,
                    isSync INTEGER NOT NULL DEFAULT 0
                )
            """)
            database.execSQL("""
                INSERT INTO category_new (categoryId, name, color, icon, type, status, isPredefined, createdAt, updatedAt, isSync)
                SELECT categoryId, name, color, icon, type, status, isPredefined, createdAt, updatedAt, isSync FROM category
            """)
            database.execSQL("DROP TABLE category")
            database.execSQL("ALTER TABLE category_new RENAME TO category")
        }
    }

    /**
     * Migration from version 4 to 5:
     * Adds a `serverId` column to the `category` table.
     * This is used for synchronization with remote database.
     */
    val MIGRATION_4_5 = object : Migration(4, 5) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE category ADD COLUMN serverId TEXT")
        }
    }

    /**
     * Provides the Room database instance with all migrations applied.
     */
    @Provides
    @Singleton
    fun provideDatabase(app: Application): MyRiyalDatabase {
        return Room.databaseBuilder(
            app,
            MyRiyalDatabase::class.java,
            "myRiyalDB"
        ).addMigrations(
            MIGRATION_1_2,
            MIGRATION_2_3,
            MIGRATION_3_4,
            MIGRATION_4_5
        )
            .build()
    }

    // Provide DAOs

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
