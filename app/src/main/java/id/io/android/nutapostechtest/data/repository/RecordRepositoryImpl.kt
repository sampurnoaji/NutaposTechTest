package id.io.android.nutapostechtest.data.repository

import id.io.android.nutapostechtest.data.source.RecordLocalDataSource
import id.io.android.nutapostechtest.domain.model.Record
import id.io.android.nutapostechtest.domain.repository.RecordRepository
import id.io.android.nutapostechtest.util.DatePattern
import id.io.android.nutapostechtest.util.toReadableString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class RecordRepositoryImpl @Inject constructor(private val localDataSource: RecordLocalDataSource) :
    RecordRepository {
    override fun getRecords(): Flow<List<Record>> {
        return localDataSource.getRecords().map {
            it.map { entity ->
                entity.toDomain()
            }
        }
    }

    override suspend fun insertRecord(record: Record) {
        val dateNumber = Date().toReadableString(DatePattern.NOMOR)

        val latestInsertRecordDate = localDataSource.getLatestInsertRecordDate()
        val today = Date().toReadableString(DatePattern.DATABASE)
        val count: Int =
            if (latestInsertRecordDate != today) 1
            else localDataSource.getRecordNumberInDay() + 1

        localDataSource.insertProductToBasket(
            record.toEntity().copy(
                nomor = "UM/$dateNumber/$count"
            )
        )
        localDataSource.setLatestInsertRecordDate(Date().toReadableString(DatePattern.DATABASE))
        localDataSource.setRecordNumberInDay(count)
    }

    override fun setLatestInsertRecordDate(date: String) {
        localDataSource.setLatestInsertRecordDate(date)
    }

    override fun getLatestInsertRecordDate(): String = localDataSource.getLatestInsertRecordDate()

    override fun setRecordNumberInDay(count: Int) {
        localDataSource.setRecordNumberInDay(count)
    }

    override fun getRecordNumberInDay(): Int = localDataSource.getRecordNumberInDay()
}