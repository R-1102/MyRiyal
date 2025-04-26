package com.example.myriyal.screens.categories.data.repository

import com.example.myriyal.screens.categories.data.local.TrackerDao
import com.example.myriyal.screens.categories.data.local.TrackerEntity
import com.example.myriyal.screens.categories.domian.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of [TrackerRepository] that handles tracker-related database operations.
 *
 * Acts as the data source for use cases related to budget tracking.
 * Connects the domain layer to the DAO (Room database).
 *
 * Data flow:
 * - Fetches from / Sends to: [TrackerDao]
 * - Called by: UseCases → ViewModel → UI
 */
class TrackerRepositoryImpl @Inject constructor(
    private val dao: TrackerDao
) : TrackerRepository {

    /**
     * Inserts a new tracker or replaces an existing one.
     *
     * Called by: InsertTrackerUseCase, InsertWithTrackerUseCase
     * Sends data to: TrackerDao.insertTracker
     */
    override suspend fun insertTracker(tracker: TrackerEntity) {
        dao.insertTracker(tracker)
    }

    /**
     * Retrieves a tracker linked to the given category ID.
     *
     * Called by: GetTrackerByCategoryIdUseCase → CategoryViewModel
     * Fetches from: TrackerDao.getTrackerByCategoryId
     */
    override fun getTrackerByCategoryId(categoryId: Int): Flow<TrackerEntity?> {
        return dao.getTrackerByCategoryId(categoryId)
    }

    /**
     * Updates an existing tracker in the database.
     *
     * Called by: UpdateTrackerUseCase → CategoryViewModel
     * Sends data to: TrackerDao.updateTracker
     */
    override suspend fun updateTracker(tracker: TrackerEntity) {
        dao.updateTracker(tracker)
    }
}
