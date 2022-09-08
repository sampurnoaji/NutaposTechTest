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
                tvNetSales.text = (record.jumlah / 1.1).roundToLong().toRupiah()
            }
        }

        fun Long.toRupiah(): String {
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