package id.io.android.nutapostechtest.presentation.test1.list

interface MainContract {
    interface Presenter {
        fun onGetRecords()
    }

    interface View {
        fun onObserveRecords()
    }
}