package id.io.android.nutapostechtest.domain.model

import id.io.android.nutapostechtest.data.model.RecordEntity
import id.io.android.nutapostechtest.util.DatePattern
import id.io.android.nutapostechtest.util.toReadableString
import java.util.Date

data class Record(
    val uangMasukId: String,
    val terimaDari: String,
    val keterangan: String,
    val jumlah: Long,
    val tanggal: Date? = null,
    val nomor: String? = null,
    val rekening: Rekening? = null
) {
    fun toEntity() = RecordEntity(
        uangMasukId = uangMasukId,
        terimaDari = terimaDari,
        keterangan = keterangan,
        jumlah = jumlah,
        tanggal = tanggal?.toReadableString(DatePattern.DATABASE),
        nomor = nomor
    )
}
