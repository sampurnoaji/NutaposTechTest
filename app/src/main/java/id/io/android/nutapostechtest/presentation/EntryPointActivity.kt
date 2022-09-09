package id.io.android.nutapostechtest.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.io.android.nutapostechtest.databinding.ActivityEntryPointBinding
import id.io.android.nutapostechtest.presentation.test1.list.MainActivity
import id.io.android.nutapostechtest.util.viewBinding

class EntryPointActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityEntryPointBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnTest1.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}