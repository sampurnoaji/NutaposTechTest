package id.io.android.nutapostechtest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.io.android.nutapostechtest.data.repository.RecordRepositoryImpl
import id.io.android.nutapostechtest.domain.repository.RecordRepository

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindRecordRepository(recordRepositoryImpl: RecordRepositoryImpl): RecordRepository
}