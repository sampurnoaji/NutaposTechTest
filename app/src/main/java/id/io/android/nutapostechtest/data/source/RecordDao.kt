package id.io.android.nutapostechtest.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.io.android.nutapostechtest.data.model.RecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertRecord(recordEntity: RecordEntity)

    @Query("SELECT * FROM RecordEntity")
    fun getRecords(): Flow<List<RecordEntity>>
}