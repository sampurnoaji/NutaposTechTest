package id.io.android.nutapostechtest.data.repository

import id.io.android.nutapostechtest.data.source.RecordLocalDataSource
import id.io.android.nutapostechtest.domain.model.Record
import id.io.android.nutapostechtest.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
        localDataSource.insertProductToBasket(record.toEntity())
    }
}