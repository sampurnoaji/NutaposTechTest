package id.io.android.nutapostechtest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.io.android.nutapostechtest.domain.model.Record

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
    val jumlah: Long
) {
    fun toDomain() = Record(
        uangMasukId = uangMasukId,
        terimaDari = terimaDari,
        keterangan = keterangan,
        jumlah = jumlah
    )
}
