package id.io.android.nutapostechtest.domain.repository

import id.io.android.nutapostechtest.domain.model.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    fun getRecords(): Flow<List<Record>>
    suspend fun insertRecord(record: Record)

    fun setLatestInsertRecordDate(date: String)
    fun getLatestInsertRecordDate(): String
    fun setRecordNumberInDay(count: Int)
    fun getRecordNumberInDay(): Int
}