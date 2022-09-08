package id.io.android.nutapostechtest.data.source

import id.io.android.nutapostechtest.data.model.RecordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecordLocalDataSource @Inject constructor(private val dao: RecordDao) {
    fun getRecords(): Flow<List<RecordEntity>> {
        return dao.getRecords()
    }

    suspend fun insertProductToBasket(recordEntity: RecordEntity) {
        dao.insertRecord(recordEntity)
    }
}