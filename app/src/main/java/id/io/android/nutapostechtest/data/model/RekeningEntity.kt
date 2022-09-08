package id.io.android.nutapostechtest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.io.android.nutapostechtest.domain.model.Rekening

@Entity
data class RekeningEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "nama_bank")
    val namaBank: String,
    @ColumnInfo(name = "nomor_rekening")
    val nomorRekening: String,
    @ColumnInfo(name = "atas_nama")
    val atasNama: String,
) {
    fun toDomain() = Rekening(id, namaBank, nomorRekening, atasNama)
}
