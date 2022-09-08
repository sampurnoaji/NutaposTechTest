package id.io.android.nutapostechtest.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import id.io.android.nutapostechtest.data.model.RecordEntity

@Database(entities = [RecordEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun recordDao(): RecordDao
}