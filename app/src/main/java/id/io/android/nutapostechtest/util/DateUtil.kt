package id.io.android.nutapostechtest.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


private val localeIndonesia: Locale = Locale("id", "ID")

enum class DatePattern(val pattern: String) {
    DATABASE("yyyy-MM-dd"), // 2020-12-01
    NOMOR("yyMMdd"),
}

fun Date.toReadableString(pattern: DatePattern): String {
    return try {
        val formatter = SimpleDateFormat(pattern.pattern, localeIndonesia)
        formatter.format(this)
    } catch (e: Exception) {
        "Wrong Date pattern!"
    }
}

fun String.toNullableDate(pattern: DatePattern): Date? {
    return if (isNullOrEmpty()) null else {
        val formatter = SimpleDateFormat(pattern.pattern, localeIndonesia)
        formatter.parse(this)
    }
}