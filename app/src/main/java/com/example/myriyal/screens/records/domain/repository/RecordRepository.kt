package com.example.myriyal.screens.records.domain.repository

import com.example.myriyal.core.local.entities.RecordEntity
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    suspend fun insert(record: RecordEntity)
    suspend fun update(record: RecordEntity)
    suspend fun delete(record: RecordEntity)
    suspend fun getRecordById(recordId: Int): RecordEntity?
    fun getAllRecords(): Flow<List<RecordEntity>>
    fun getRecordsByCategory(categoryId: Int): Flow<List<RecordEntity>>
}