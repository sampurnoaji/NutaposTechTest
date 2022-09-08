package id.io.android.nutapostechtest.domain.model

import id.io.android.nutapostechtest.data.model.RecordEntity

data class Record(
    val uangMasukId: String,
    val terimaDari: String,
    val keterangan: String,
    val jumlah: Long
) {
    fun toEntity() = RecordEntity(
        uangMasukId = uangMasukId,
        terimaDari = terimaDari,
        keterangan = keterangan,
        jumlah = jumlah
    )
}
