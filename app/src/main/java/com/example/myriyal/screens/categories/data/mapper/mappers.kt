package com.example.myriyal.screens.categories.data.mapper

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.categories.data.model.CategoryDto
import com.example.myriyal.screens.categories.domian.model.CategoryStatus
import com.example.myriyal.screens.categories.domian.model.CategoryType


// From API ---> Local Database (DAO)
fun CategoryDto.toEntity(existingEntity: CategoryEntity?): CategoryEntity {
    return CategoryEntity(
        serverId = id,
        name = name,
        color = color,
        icon = icon,
        categoryId = existingEntity?.categoryId ?: 0,
        status = existingEntity?.status ?: CategoryStatus.ACTIVE,
        type = existingEntity?.type ?: CategoryType.EXPENSE,
        isPredefined = existingEntity?.isPredefined ?: true,
        createdAt = existingEntity?.createdAt ?: System.currentTimeMillis(),
        updatedAt = existingEntity?.updatedAt ?: System.currentTimeMillis(),
        isSync = false
    )
}



// From Local Database (DAO) ---> API
fun CategoryEntity.toDto(): CategoryDto {
    return CategoryDto(
        id = serverId,
        name = name,
        color = color,
        icon = icon,
        budgetLimit = null
    )
}