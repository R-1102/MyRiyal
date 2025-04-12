package com.example.myriyal.core.local.db

import androidx.room.TypeConverter
import com.example.myriyal.core.local.enums.CategoryStatus
import com.example.myriyal.core.local.enums.CategoryType

class Converters {

    @TypeConverter
    fun fromStatus(value: CategoryStatus): String = value.name

    @TypeConverter
    fun toStatus(value: String): CategoryStatus = CategoryStatus.valueOf(value)

    @TypeConverter
    fun fromType(value: CategoryType): String = value.name

    @TypeConverter
    fun toType(value: String): CategoryType = CategoryType.valueOf(value)
}
