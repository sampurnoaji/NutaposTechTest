package id.io.android.nutapostechtest.presentation.insert

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import dagger.hilt.android.AndroidEntryPoint
import id.io.android.nutapostechtest.R
import id.io.android.nutapostechtest.databinding.ActivityInsertRecordBinding
import id.io.android.nutapostechtest.util.Dialog
import id.io.android.nutapostechtest.util.LoadState
import id.io.android.nutapostechtest.util.viewBinding
import javax.inject.Inject

@AndroidEntryPoint
class InsertRecordActivity : AppCompatActivity(), Contract.View {

    private val binding by viewBinding(ActivityInsertRecordBinding::inflate)

    @Inject
    lateinit var presenter: InsertRecordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        setupActionView()
    }

    private fun setupView() {
        with(binding.toolbar) {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    private fun setupActionView() {
        binding.etUangMasukId.doOnTextChanged { text, _, _, _ ->
            presenter.onUangMasukIdChanged(text.toString().trim())
        }
        binding.etTerimaDari.doOnTextChanged { text, _, _, _ ->
            presenter.onTerimaDariChanged(text.toString().trim())
        }
        binding.etKeterangan.doOnTextChanged { text, _, _, _ ->
            presenter.onKeteranganChanged(text.toString().trim())
        }
        binding.etJumlah.doOnTextChanged { text, _, _, _ ->
            presenter.onJumlahChanged(text.toString().trim())
        }
        binding.btnInsert.setOnClickListener {
            presenter.onInsertRecord()
        }
    }

    override fun observeInsertRecord() {
        presenter.insertRecord.observe(this) {
            binding.pbLoading.isVisible = it is LoadState.Loading
            when (it) {
                is LoadState.Success -> Dialog(
                    context = this,
                    message = getString(R.string.record_insert_success),
                    positiveButtonText = getString(R.string.close),
                    positiveAction = {
                        setResult(Activity.RESULT_OK)
                        finish()
                    }
                ).show()
                is LoadState.Error -> Dialog(
                    context = this,
                    message = getString(R.string.record_insert_failed),
                    positiveButtonText = getString(R.string.close),
                ).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}