package com.example.myriyal.core.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myriyal.core.local.entities.TrackerEntity
import kotlinx.coroutines.flow.Flow

// DAO for the "trackers" table.
// Provides database operations for budget trackers linked to categories.
//
// Data flows:
// - Called by: TrackerRepositoryImpl
// - Receives requests from: TrackerViewModel → Repository → DAO
// - Sends data to: UI screen (e.g., BudgetTrackerScreen)

@Dao
interface TrackerDao {

    // Inserts a new tracker or replaces an existing one with the same primary key.
    // Called by: TrackerRepositoryImpl.insertTracker
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracker(tracker: TrackerEntity)

    // Retrieves all trackers from the database, ordered by creation time (latest first).
    // Returned to: TrackerViewModel → UI screen
    @Query("SELECT * FROM trackers ORDER BY createdAt DESC")
    fun getTrackers(): Flow<List<TrackerEntity>>

    // Deletes a specific tracker from the database.
    // Called by: TrackerRepositoryImpl.deleteTracker
    @Delete
    suspend fun deleteTracker(tracker: TrackerEntity)
}
