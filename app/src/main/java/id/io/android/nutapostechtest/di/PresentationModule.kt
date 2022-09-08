package id.io.android.nutapostechtest.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import id.io.android.nutapostechtest.data.repository.RecordRepositoryImpl
import id.io.android.nutapostechtest.presentation.insert.Contract
import id.io.android.nutapostechtest.presentation.insert.InsertRecordActivity
import id.io.android.nutapostechtest.presentation.insert.InsertRecordPresenter

@Module
@InstallIn(ActivityComponent::class)
object PresentationModule {
    @Provides
    fun provideInsertRecordActivity(activity: Activity): InsertRecordActivity =
        activity as InsertRecordActivity

    @Provides
    fun provideInsertRecordView(view: InsertRecordActivity): Contract.View = view

    @Provides
    fun provideInsertRecordPresenter(
        view: Contract.View,
        repository: RecordRepositoryImpl
    ): InsertRecordPresenter = InsertRecordPresenter(view, repository)
}