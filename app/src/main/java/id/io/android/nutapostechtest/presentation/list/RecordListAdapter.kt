package id.io.android.nutapostechtest.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.io.android.nutapostechtest.databinding.ItemRecordBinding
import id.io.android.nutapostechtest.domain.model.Record
import java.util.Locale
import kotlin.math.roundToLong

class RecordListAdapter : ListAdapter<Record, RecordListAdapter.ContentViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecordBinding.inflate(inflater, parent, false)
        return ContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ContentViewHolder(private val binding: ItemRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(record: Record) {
            with(binding) {
                tvId.text = record.uangMasukId
                tvTerimaDari.text = record.terimaDari
                tvKeterangan.text = record.keterangan
                tvJumlah.text = record.jumlah.toRupiah()
                tvNomor.text = record.nomor

                // Pajak 10%
                val pajak = 0.1f
                tvNetSales.text = record.jumlah.calculateNetSales(pajak)

                // Diskon 10% + 10%
                val diskon = listOf(0.1f, 0.1f)
                tvSetelahDiskon.text = record.jumlah.calculateDiscount(diskon).first
                tvDiskon.text = record.jumlah.calculateDiscount(diskon).second

                // Markup 10%, Share 10%
                val markup = 0.1f
                val share = 0.1f
                tvNetResto.text = record.jumlah.calculateRestoOjol(markup, share).first
                tvShareOjol.text = record.jumlah.calculateRestoOjol(markup, share).second
            }
        }

        private fun Long.calculateNetSales(pajak: Float): String {
            val net = this / pajak
            return net.roundToLong().toRupiah()
        }

        private fun Long.calculateDiscount(discounts: List<Float>): Pair<String, String> {
            var final = this
            var diskon = 0L
            discounts.forEach {
                diskon += ((final - diskon) * it).roundToLong()
                final -= diskon
            }
            return Pair(final.toRupiah(), diskon.toRupiah())
        }

        private fun Long.calculateRestoOjol(markup: Float, share: Float): Pair<String, String> {
            val afterMarkup = this + (this * markup)
            val resto = (afterMarkup * (1 - share)).roundToLong()
            val ojol = (afterMarkup * share).roundToLong()
            return Pair(resto.toRupiah(), ojol.toRupiah())
        }

        private fun Long.toRupiah(): String {
            return try {
                val localeId = Locale("id", "ID")
                "Rp " + String.format(localeId, "%,d", this)
            } catch (e: Exception) {
                "-"
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Record>() {
            override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem.uangMasukId == newItem.uangMasukId
            }
        }
    }
}