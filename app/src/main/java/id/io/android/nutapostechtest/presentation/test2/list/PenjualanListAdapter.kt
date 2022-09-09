package id.io.android.nutapostechtest.presentation.test2.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.io.android.nutapostechtest.databinding.ItemListChildBinding
import id.io.android.nutapostechtest.databinding.ItemListHeaderBinding
import java.util.Locale
import kotlin.math.roundToLong

class PenjualanListAdapter(private val items: List<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == 0)
            HeaderViewHolder(ItemListHeaderBinding.inflate(inflater, parent, false))
        else
            ChildViewHolder(ItemListChildBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (items[position].viewType == 0) (holder as HeaderViewHolder).bind(items[position])
        else (holder as ChildViewHolder).bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }

    class HeaderViewHolder(private val binding: ItemListHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.tvTanggal.text = item.tanggal
            binding.tvJumlah.text = item.jumlah.toRupiah()
        }
    }

    class ChildViewHolder(private val binding: ItemListChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            item.penjualan?.let {
                var pelanggan = ""
                if (it.Pelanggan.isNotEmpty()) pelanggan += "Pelanggan: ${it.Pelanggan}"
                if (it.Meja.isNotEmpty()) pelanggan += "    Meja: ${it.Meja}"
                if (it.Driver.isNotEmpty()) pelanggan += "Driver: ${it.Driver}"
                if (it.Pemesan.isNotEmpty()) pelanggan += "    Pemesan: ${it.Pemesan}"

                binding.tvPelanggan.text = pelanggan
                binding.tvJumlah.text = it.Total.roundToLong().toRupiah()
                binding.tvJam.text = it.Jam
            }
        }
    }

    data class Item(
        val viewType: Int,
        val penjualan: Penjualan? = null,
        val tanggal: String = "",
        val jumlah: Long = 0
    )
}

fun Long.toRupiah(): String {
    return try {
        val localeId = Locale("id", "ID")
        "Rp " + String.format(localeId, "%,d", this)
    } catch (e: Exception) {
        "-"
    }
}