package com.example.myriyal.screens.categories.domian.useCases

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.categories.data.local.TrackerEntity
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import com.example.myriyal.screens.categories.domian.repository.TrackerRepository
import javax.inject.Inject

/**
 * Use case for inserting a category and optionally inserting an associated tracker.
 *
 * Data flow:
 * - Sends data to: CategoryRepository → CategoryDao
 * - Sends data to: TrackerRepository → TrackerDao (only if tracker input is valid)
 * - Called from: CategoryViewModel during form submission
 */
class InsertCategoryWithTrackerUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val trackerRepository: TrackerRepository
) {
    /**
     * Inserts a category, and if tracker input is valid, inserts a related tracker.
     *
     * @param category The category to insert.
     * @param trackerBudget Optional budget value for the tracker.
     * @param trackerStartDate Optional start date for the tracker.
     * @return The ID of the newly inserted category.
     */
    suspend operator fun invoke(
        category: CategoryEntity,
        trackerBudget: String?,
        trackerStartDate: Long?
    ): Long {
        // Insert the category and retrieve the generated ID
        val newCategoryId = categoryRepository.insertCategory(category)

        // If valid tracker data is provided, insert the tracker linked to the category
        if (!trackerBudget.isNullOrBlank() && trackerStartDate != null) {
            val tracker = TrackerEntity(
                categoryId = newCategoryId.toInt(),
                budgetAmount = trackerBudget.toDoubleOrNull() ?: 0.0,
                startDate = trackerStartDate,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            trackerRepository.insertTracker(tracker)
        }

        return newCategoryId
    }
}
