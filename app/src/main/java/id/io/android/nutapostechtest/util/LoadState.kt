package id.io.android.nutapostechtest.util

sealed class LoadState<out T> {
    object Loading : LoadState<Nothing>()
    data class Success<out T>(val data: T?) : LoadState<T>()
    data class Error(val message: String? = null) : LoadState<Nothing>()
}
