package id.io.android.nutapostechtest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.io.android.nutapostechtest.domain.model.Record
import id.io.android.nutapostechtest.util.DatePattern
import id.io.android.nutapostechtest.util.toNullableDate

@Entity
data class RecordEntity(
    @PrimaryKey
    @ColumnInfo(name = "uang_masuk_id")
    val uangMasukId: String,
    @ColumnInfo(name = "terima_dari")
    val terimaDari: String,
    @ColumnInfo(name = "keterangan")
    val keterangan: String,
    @ColumnInfo(name = "jumlah")
    val jumlah: Long,
    @ColumnInfo(name = "tanggal")
    val tanggal: String? = null,
    @ColumnInfo(name = "nomor")
    val nomor: String? = null,
    @ColumnInfo(name = "rekening_id")
    val rekeningId: String? = null
) {
    fun toDomain() = Record(
        uangMasukId = uangMasukId,
        terimaDari = terimaDari,
        keterangan = keterangan,
        jumlah = jumlah,
        tanggal = tanggal?.toNullableDate(DatePattern.DATABASE),
        nomor = nomor
    )
}
