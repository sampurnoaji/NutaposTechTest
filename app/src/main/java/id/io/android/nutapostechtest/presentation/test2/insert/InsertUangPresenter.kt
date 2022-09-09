package id.io.android.nutapostechtest.presentation.test2.insert

import javax.inject.Inject

class InsertUangPresenter @Inject constructor(
    private var view: InsertUangContract.View?,
) : InsertUangContract.Presenter {

    private var jumlah = 0L

    init {
        view?.onJumlahChanged(jumlah)
    }

    override fun onJumlahChanged(jumlah: Long) {
        view?.onJumlahChanged(jumlah)
        this.jumlah = jumlah
    }
}