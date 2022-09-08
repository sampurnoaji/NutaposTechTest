package id.io.android.nutapostechtest.presentation.list

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import id.io.android.nutapostechtest.domain.model.Record
import id.io.android.nutapostechtest.domain.repository.RecordRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private var view: MainContract.View?,
    private val repository: RecordRepository
) : MainContract.Presenter {

    private val _records = MutableLiveData<List<Record>>()
    val records: LiveData<List<Record>>
        get() = _records

    override fun onGetRecords() {
        view?.onObserveRecords()
        (view as LifecycleOwner).lifecycleScope.launch {
            repository.getRecords().collect {
                _records.value = it
            }
        }
    }
}