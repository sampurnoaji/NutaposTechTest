package id.io.android.nutapostechtest.presentation.test2.insert

interface InsertUangContract {
    interface Presenter {
        fun onJumlahChanged(jumlah: Long)
    }

    interface View {
        fun onJumlahChanged(jumlah: Long)
    }
}