package id.io.android.nutapostechtest.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import id.io.android.nutapostechtest.data.model.RecordEntity
import id.io.android.nutapostechtest.data.model.RekeningEntity

@Database(entities = [RecordEntity::class, RekeningEntity::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun recordDao(): RecordDao
}