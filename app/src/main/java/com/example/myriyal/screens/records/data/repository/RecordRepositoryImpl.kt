package com.example.myriyal.screens.records.data.repository

import android.content.Context
import com.example.myriyal.core.local.db.DatabaseProvider
import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.records.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class RecordRepositoryImpl(private val context: Context) : RecordRepository {

    private val dao = DatabaseProvider.getDatabase(context).recordDao()

    override suspend fun insert(record: RecordEntity) {
        dao.insertRecord(record)
    }

    override suspend fun update(record: RecordEntity) {
        // You can add update logic here if needed (e.g., dao.updateRecord())
        dao.insertRecord(record) // Replace strategy acts like update
    }

    override suspend fun delete(record: RecordEntity) {
        dao.deleteRecord(record)
    }

    override suspend fun getRecordById(recordId: Int): RecordEntity? {
        return dao.getRecordById(recordId)
    }

    override fun getAllRecords(): Flow<List<RecordEntity>> {
        return dao.getAllRecords()
    }

    override fun getRecordsByCategory(categoryId: Int): Flow<List<RecordEntity>> {
        return dao.getRecordsByCategory(categoryId)
    }
}