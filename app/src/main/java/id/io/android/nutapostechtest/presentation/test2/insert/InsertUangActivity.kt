package id.io.android.nutapostechtest.presentation.test2.insert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import dagger.hilt.android.AndroidEntryPoint
import id.io.android.nutapostechtest.R
import id.io.android.nutapostechtest.databinding.ActivityInsertUangBinding
import id.io.android.nutapostechtest.presentation.test1.insert.InsertRecordPresenter
import id.io.android.nutapostechtest.util.viewBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class InsertUangActivity : AppCompatActivity(), InsertUangContract.View {

    private val binding by viewBinding(ActivityInsertUangBinding::inflate)

    @Inject
    lateinit var presenter: InsertUangPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupVIew()
    }

    private fun setupVIew() {
        binding.etMasukKe.setText("Kasir perangkat 1")

        binding.etJumlah.doOnTextChanged { text, _, _, _ ->
            presenter.onJumlahChanged(text.toString().removeThousandSeparator() ?: 0)
        }

        binding.tvBack.setOnClickListener { onBackPressed() }
    }

    override fun onJumlahChanged(jumlah: Long) {
        binding.etJumlah.apply {
            if (text.toString().removeThousandSeparator() != jumlah)
                setText(jumlah.toString().addThousandSeparator())
        }
    }

    private fun String.addThousandSeparator(): String {
        return try {
            val number = this.toLong()
            val symbol = DecimalFormatSymbols(Locale("id", "ID"))
            val formatter = DecimalFormat("#,###,###", symbol)
            return formatter.format(number)
        } catch (e: Exception) {
            this
        }
    }

    private fun String.removeThousandSeparator(): Long? {
        return try {
            this.replace(".", "").toLong()
        } catch (e: Exception) {
            null
        }
    }
}