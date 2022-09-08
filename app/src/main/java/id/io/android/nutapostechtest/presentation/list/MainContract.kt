package id.io.android.nutapostechtest.presentation.list

interface MainContract {
    interface Presenter {
        fun onGetRecords()
    }

    interface View {
        fun onObserveRecords()
    }
}