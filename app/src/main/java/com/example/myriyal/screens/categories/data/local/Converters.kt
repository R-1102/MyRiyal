package com.example.myriyal.screens.categories.data.local

import androidx.room.TypeConverter
import com.example.myriyal.screens.categories.domian.model.CategoryStatus
import com.example.myriyal.screens.categories.domian.model.CategoryType

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
