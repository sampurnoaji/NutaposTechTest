package id.io.android.nutapostechtest.data.source

import android.content.SharedPreferences
import id.io.android.nutapostechtest.data.model.RecordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecordLocalDataSource @Inject constructor(
    private val dao: RecordDao,
    private val sharedPreferences: SharedPreferences,
    private val sharedEditor: SharedPreferences.Editor
) {

    companion object {
        private const val PREF_KEY_LATEST_INSERT_RECORD_DATE = "latest_date"
        private const val PREF_KEY_RECORD_NUMBER_IN_DAY = "record_number"
    }

    fun getRecords(): Flow<List<RecordEntity>> {
        return dao.getRecords()
    }

    suspend fun insertProductToBasket(recordEntity: RecordEntity) {
        dao.insertRecord(recordEntity)
    }

    fun setLatestInsertRecordDate(date: String) {
        sharedEditor.putString(PREF_KEY_LATEST_INSERT_RECORD_DATE, date)
        sharedEditor.apply()
    }

    fun getLatestInsertRecordDate(): String =
        sharedPreferences.getString(PREF_KEY_LATEST_INSERT_RECORD_DATE, "").orEmpty()

    fun setRecordNumberInDay(count: Int) {
        sharedEditor.putInt(PREF_KEY_RECORD_NUMBER_IN_DAY, count)
        sharedEditor.apply()
    }

    fun getRecordNumberInDay(): Int = sharedPreferences.getInt(PREF_KEY_RECORD_NUMBER_IN_DAY, 0)
}