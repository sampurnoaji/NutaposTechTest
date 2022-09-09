package id.io.android.nutapostechtest.presentation.test1.insert

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import id.io.android.nutapostechtest.domain.model.Record
import id.io.android.nutapostechtest.domain.repository.RecordRepository
import id.io.android.nutapostechtest.util.LoadState
import kotlinx.coroutines.launch
import javax.inject.Inject

class InsertRecordPresenter @Inject constructor(
    private var view: InsertRecordContract.View?,
    private val repository: RecordRepository
) : InsertRecordContract.Presenter {

    private val _insertRecord = MutableLiveData<LoadState<Boolean>>()
    val insertRecord: LiveData<LoadState<Boolean>>
        get() = _insertRecord

    private var uangMasukId = ""
    private var terimaDari = ""
    private var keterangan = ""
    private var jumlah = ""

    override fun onUangMasukIdChanged(id: String) {
        this.uangMasukId = id
    }

    override fun onTerimaDariChanged(terimaDari: String) {
        this.terimaDari = terimaDari
    }

    override fun onKeteranganChanged(keterangan: String) {
        this.keterangan = keterangan
    }

    override fun onJumlahChanged(jumlah: String) {
        this.jumlah = jumlah
    }

    override fun onInsertRecord() {
        view?.observeInsertRecord()
        _insertRecord.value = LoadState.Loading
        (view as LifecycleOwner).lifecycleScope.launch {
            _insertRecord.value = try {
                repository.insertRecord(
                    Record(
                        uangMasukId = uangMasukId,
                        terimaDari = terimaDari,
                        keterangan = keterangan,
                        jumlah = jumlah.toLongOrNull() ?: 0,
                    )
                )
                LoadState.Success(true)
            } catch (e: Exception) {
                LoadState.Error(message = e.message)
            }
        }
    }

    override fun onDestroy() {
        view = null
    }
}
