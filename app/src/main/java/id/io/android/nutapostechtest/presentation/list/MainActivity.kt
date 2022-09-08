package id.io.android.nutapostechtest.presentation.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.io.android.nutapostechtest.databinding.ActivityMainBinding
import id.io.android.nutapostechtest.presentation.insert.InsertRecordActivity
import id.io.android.nutapostechtest.util.viewBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainContract.View {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    @Inject
    lateinit var presenter: MainPresenter

    private val recordListAdapter by lazy { RecordListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupActionView()
        presenter.onGetRecords()
    }

    private fun setupActionView() {
        binding.rvRecords.apply {
            adapter = recordListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        binding.fabAdd.setOnClickListener {
            launcher.launch(Intent(this, InsertRecordActivity::class.java))
        }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        }

    override fun onObserveRecords() {
        presenter.records.observe(this) {
            binding.groupEmpty.isVisible = it.isEmpty()
            binding.rvRecords.isVisible = it.isNotEmpty()
            recordListAdapter.submitList(it)
        }
    }
}