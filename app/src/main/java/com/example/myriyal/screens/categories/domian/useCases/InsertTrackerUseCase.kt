package com.example.myriyal.screens.categories.domian.useCases

import com.example.myriyal.core.local.entities.TrackerEntity
import com.example.myriyal.screens.categories.domian.repository.TrackerRepository
import javax.inject.Inject

/**
 * Use case for inserting a tracker entity into the database.
 *
 * Data flow:
 * - Sends data to: TrackerRepository → TrackerDao (Room)
 * - Called from: CategoryViewModel when tracker is created independently or with a category
 */
class InsertTrackerUseCase @Inject constructor(
    private val trackerRepository: TrackerRepository
) {
    /**
     * Executes the insert operation for the given tracker.
     *
     * @param tracker The tracker entity to insert.
     */
    suspend operator fun invoke(tracker: TrackerEntity) {
        trackerRepository.insertTracker(tracker)
    }
}
