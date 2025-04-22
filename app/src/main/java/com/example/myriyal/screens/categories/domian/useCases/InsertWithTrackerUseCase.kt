package com.example.myriyal.screens.categories.domian.useCases

import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.entities.TrackerEntity
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import com.example.myriyal.screens.categories.domian.repository.TrackerRepository
import javax.inject.Inject

/**
 * Use case for inserting a category along with optional tracker data.
 *
 * Data flow:
 * - Sends category to: CategoryRepository → CategoryDao
 * - Sends tracker (conditionally) to: TrackerRepository → TrackerDao
 * - Called from: CategoryViewModel when saving a category through the form
 */
class InsertWithTrackerUseCase @Inject constructor(
    private val categoryRepo: CategoryRepository,
    private val trackerRepo: TrackerRepository
) {
    /**
     * Inserts a category into the database and conditionally inserts a tracker if a valid budget is provided.
     *
     * @param category The category entity to insert.
     * @param trackerBudget The user-entered budget amount as a string.
     * @param trackerStartDate The optional start date for the tracker.
     * @return The generated ID of the inserted category.
     */
    suspend operator fun invoke(
        category: CategoryEntity,
        trackerBudget: String,
        trackerStartDate: Long?
    ): Long {
        // Insert the category and retrieve its generated ID
        val categoryId = categoryRepo.insertCategory(category)

        // Convert the budget to a number and validate before inserting tracker
        val budget = trackerBudget.toDoubleOrNull() ?: 0.0
        if (budget > 0.0) {
            val tracker = TrackerEntity(
                categoryId = categoryId.toInt(),
                budgetAmount = budget,
                startDate = trackerStartDate ?: System.currentTimeMillis(),
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            trackerRepo.insertTracker(tracker)
        }

        return categoryId
    }
}
