package id.io.android.nutapostechtest.presentation.insert

interface InsertRecordContract {
    interface Presenter {
        fun onUangMasukIdChanged(id: String)
        fun onTerimaDariChanged(terimaDari: String)
        fun onKeteranganChanged(keterangan: String)
        fun onJumlahChanged(jumlah: String)
        fun onInsertRecord()
        fun onDestroy()
    }

    interface View {
        fun observeInsertRecord()
    }
}