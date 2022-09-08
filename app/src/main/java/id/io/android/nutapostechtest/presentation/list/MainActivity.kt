package id.io.android.nutapostechtest.presentation.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import id.io.android.nutapostechtest.databinding.ActivityMainBinding
import id.io.android.nutapostechtest.presentation.insert.InsertRecordActivity
import id.io.android.nutapostechtest.util.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupActionView()
    }

    private fun setupActionView() {
        binding.fabAdd.setOnClickListener {
            launcher.launch(Intent(this, InsertRecordActivity::class.java))
        }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        }
}