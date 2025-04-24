package com.example.myriyal.screens.categories.data.model

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.core.local.enums.CategoryStatus
import com.example.myriyal.core.local.enums.CategoryType


// From API ---> Local Database (DAO)
fun CategoryDto.toEntity(existingEntity: CategoryEntity?): CategoryEntity {
    return CategoryEntity(
        name = name,
        color = color,
        icon = icon,
        categoryId = existingEntity?.categoryId ?: 0,
        status = existingEntity?.status ?:CategoryStatus.ACTIVE,
        type = existingEntity?.type ?: CategoryType.EXPENSE,
        isPredefined =  existingEntity?.isPredefined ?:true,
        createdAt = existingEntity?.createdAt?:System.currentTimeMillis(),
        updatedAt = existingEntity?.updatedAt?:System.currentTimeMillis(),
        isSynced = false
    )
}



// From Local Database (DAO) ---> API
fun CategoryEntity.toDto():CategoryDto{
    return CategoryDto(
        name = name,
        color = color,
        icon = icon,
        budgetLimit = null
    )
}