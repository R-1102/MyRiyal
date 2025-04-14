package com.example.myriyal.core.local.db

import android.content.Context
import androidx.room.Room

// Singleton object to provide a single instance of the Room database across the entire app.
// Used when not using a dependency injection framework like Hilt.
//
// Data flows:
// - Called by: Repositories (e.g., CategoryRepositoryImpl, RecordRepositoryImpl)
// - Returns: MyRiyalDatabase instance → used to access DAO interfaces

object DatabaseProvider {

    // Holds the single instance of the Room database
    private var dbInstance: MyRiyalDatabase? = null

    // Returns the existing database instance or creates it if not already initialized
    // Called by: Repository classes that need access to a DAO (e.g., categoryDao())
    fun getDatabase(context: Context): MyRiyalDatabase {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(
                context.applicationContext,
                MyRiyalDatabase::class.java,
                "myRiyalDB" // Name of the physical database file stored on device
            )
                // Allows Room to reset the database if a schema version mismatch occurs
                .fallbackToDestructiveMigration() // -----------------------(Need to fix) Hover on function to see the problem-------------------------------
                .build()
        }
        return dbInstance!!
    }
}
