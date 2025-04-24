package com.example.myriyal.screens.categories.data.local

import com.example.myriyal.core.local.enums.CategoryStatus
import com.example.myriyal.core.local.enums.CategoryType

object PredefinedCategoryProvider {
    fun getCategories(): List<CategoryEntity> {
        val timestamp = System.currentTimeMillis()

        return listOf(
            CategoryEntity(
                name = "Food",
                color = "#FF5733",
                icon = "🍔",
                status = CategoryStatus.ACTIVE,
                type = CategoryType.EXPENSE,
                isPredefined = true,
                createdAt = timestamp,
                updatedAt = timestamp
            ),
            CategoryEntity(
                name = "Salary",
                color = "#4CAF50",
                icon = "💰",
                status = CategoryStatus.ACTIVE,
                type = CategoryType.INCOME,
                isPredefined = true,
                createdAt = timestamp,
                updatedAt = timestamp
            ),
            CategoryEntity(
                name = "Transport",
                color = "#2196F3",
                icon = "🚌",
                status = CategoryStatus.ACTIVE,
                type = CategoryType.EXPENSE,
                isPredefined = true,
                createdAt = timestamp,
                updatedAt = timestamp
            ),
            CategoryEntity(
                name = "Shopping",
                color = "#E91E63",
                icon = "🛍️",
                status = CategoryStatus.ACTIVE,
                type = CategoryType.EXPENSE,
                isPredefined = true,
                createdAt = timestamp,
                updatedAt = timestamp
            ),
            CategoryEntity(
                name = "Health",
                color = "#9C27B0",
                icon = "💊",
                status = CategoryStatus.ACTIVE,
                type = CategoryType.EXPENSE,
                isPredefined = true,
                createdAt = timestamp,
                updatedAt = timestamp
            ),
            CategoryEntity(
                name = "Gift",
                color = "#F44336",
                icon = "🎁",
                status = CategoryStatus.ACTIVE,
                type = CategoryType.INCOME,
                isPredefined = true,
                createdAt = timestamp,
                updatedAt = timestamp
            ),
            CategoryEntity(
                name = "Investment",
                color = "#3F51B5",
                icon = "📈",
                status = CategoryStatus.ACTIVE,
                type = CategoryType.INCOME,
                isPredefined = true,
                createdAt = timestamp,
                updatedAt = timestamp
            )
        )
    }
}
