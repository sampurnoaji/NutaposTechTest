package id.io.android.nutapostechtest.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.io.android.nutapostechtest.data.source.AppDatabase
import id.io.android.nutapostechtest.data.source.RecordDao
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    const val DB_NAME = "app.db"

    @Singleton
    @Provides
    fun provideDb(
        @ApplicationContext context: Context,
        provider: Provider<RecordDao>
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
//            .addCallback(DatabaseCallback(context, provider))
//            .addMigrations(MIGRATION_1_2)
            .build()
    }

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {}
    }

    @Provides
    fun provideRecordDao(db: AppDatabase): RecordDao = db.recordDao()
}