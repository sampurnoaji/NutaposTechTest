package id.io.android.nutapostechtest.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import id.io.android.nutapostechtest.data.repository.RecordRepositoryImpl
import id.io.android.nutapostechtest.presentation.test1.insert.InsertRecordContract
import id.io.android.nutapostechtest.presentation.test1.insert.InsertRecordActivity
import id.io.android.nutapostechtest.presentation.test1.insert.InsertRecordPresenter
import id.io.android.nutapostechtest.presentation.test1.list.MainActivity
import id.io.android.nutapostechtest.presentation.test1.list.MainContract
import id.io.android.nutapostechtest.presentation.test1.list.MainPresenter

@Module
@InstallIn(ActivityComponent::class)
object PresentationModule {
    @Provides
    fun provideMainActivity(activity: Activity): MainActivity =
        activity as MainActivity

    @Provides
    fun provideInsertRecordActivity(activity: Activity): InsertRecordActivity =
        activity as InsertRecordActivity

    @Provides
    fun provideInsertRecordView(view: InsertRecordActivity): InsertRecordContract.View = view

    @Provides
    fun provideMainView(view: MainActivity): MainContract.View = view

    @Provides
    fun provideInsertRecordPresenter(
        view: InsertRecordContract.View,
        repository: RecordRepositoryImpl
    ): InsertRecordPresenter = InsertRecordPresenter(view, repository)

    @Provides
    fun provideMainPresenter(
        view: MainContract.View,
        repository: RecordRepositoryImpl
    ): MainPresenter = MainPresenter(view, repository)
}