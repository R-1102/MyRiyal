package com.example.myriyal.screens.categories.domian.repository

import com.example.myriyal.core.local.entities.TrackerEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing tracker data (budget tracking linked to categories).
 *
 * Purpose:
 * - Defines all operations related to trackers in the domain layer.
 * - Abstracts data source access (Room DB via DAO) from the rest of the app.
 *
 * Implemented by: [TrackerRepositoryImpl]
 * Called by: Use cases like InsertTrackerUseCase, UpdateTrackerUseCase, GetTrackerByCategoryIdUseCase
 */
interface TrackerRepository {

    /**
     * Inserts a tracker into the database.
     *
     * Called from: [InsertTrackerUseCase] or [InsertWithTrackerUseCase]
     * Sends data to: TrackerDao.insertTracker
     */
    suspend fun insertTracker(tracker: TrackerEntity)

    /**
     * Returns a reactive stream of the tracker for a specific category ID.
     *
     * Called from: [GetTrackerByCategoryIdUseCase] → ViewModel → UI
     * Fetches from: TrackerDao.getTrackerByCategoryId
     */
    fun getTrackerByCategoryId(categoryId: Int): Flow<TrackerEntity?>

    /**
     * Updates an existing tracker.
     *
     * Called from: [UpdateTrackerUseCase] → ViewModel → UI
     * Sends data to: TrackerDao.updateTracker
     */
    suspend fun updateTracker(tracker: TrackerEntity)
}
