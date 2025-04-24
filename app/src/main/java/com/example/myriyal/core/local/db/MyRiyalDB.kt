package com.example.myriyal.core.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myriyal.core.local.dao.CategoryDao
import com.example.myriyal.core.local.dao.RecordDao
import com.example.myriyal.core.local.dao.TrackerDao
import com.example.myriyal.core.local.dao.UserDao
import com.example.myriyal.core.local.entities.UserEntity
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.core.local.entities.TrackerEntity

// ----------------------------------------------------
// Room database class that manages local persistence.
// Serves as the central access point for all DAOs.
// ----------------------------------------------------

@Database(
    // List of all entities (tables) managed by Room
    entities = [UserEntity::class, CategoryEntity::class, RecordEntity::class, TrackerEntity::class],
    version = 3, // Current schema version of the database
    exportSchema = false // Disable schema export (optional)
)
@TypeConverters(Converters::class) // Register type converters (e.g. enums)
abstract class MyRiyalDatabase : RoomDatabase() {

    // Used by UserRepository to access the user table
    // Called from: UserRepositoryImpl
    // Sends data to: UserEntity (user_profile table)
    abstract fun userDao(): UserDao

    // Used by CategoryRepository to access the category table
    // Called from: CategoryRepositoryImpl (in screens.categories.data.repository)
    // Sends data to: CategoryEntity
    // Receives data from: ViewModel (CategoryViewModel) → Repository → DAO
    abstract fun categoryDao(): CategoryDao

    // Used by RecordRepository to access the records table
    // Called from: RecordRepositoryImpl
    // Sends data to: RecordEntity
    abstract fun recordDao(): RecordDao

    // Used by TrackerRepository to access the tracker table
    // Called from: TrackerRepositoryImpl
    // Sends data to: TrackerEntity
    abstract fun trackerDao(): TrackerDao
}
