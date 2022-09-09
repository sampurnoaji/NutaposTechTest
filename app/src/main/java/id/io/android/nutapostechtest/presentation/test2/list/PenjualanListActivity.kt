package id.io.android.nutapostechtest.presentation.test2.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.io.android.nutapostechtest.databinding.ActivityPenjualanListBinding
import id.io.android.nutapostechtest.util.viewBinding
import kotlin.math.roundToLong

class PenjualanListActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityPenjualanListBinding::inflate)
    private val penjualanListAdapter by lazy { PenjualanListAdapter(populateDummyData()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvPenjualan.apply {
            adapter = penjualanListAdapter
            layoutManager = LinearLayoutManager(this@PenjualanListActivity)
        }

        binding.tvBack.setOnClickListener { onBackPressed() }
    }

    private fun populateDummyData(): List<PenjualanListAdapter.Item> {
        val raw = Penjualan.getDummyPenjualan()
        val items = mutableListOf<PenjualanListAdapter.Item>()

        val group = raw.groupBy { it.Tanggal }
        group.entries.forEach {
            items.add(
                PenjualanListAdapter.Item(
                    viewType = 0,
                    tanggal = it.key,
                    jumlah = it.value.sumOf { penjualan -> penjualan.Total }.roundToLong(),
                )
            )
            it.value.forEach { penjualan ->
                items.add(
                    PenjualanListAdapter.Item(
                        viewType = 1,
                        penjualan = penjualan
                    )
                )
            }
        }
        return items
    }
}