package io.github.fuadreza.core_android.data.vo

sealed class Results<out T> {
    object Loading : Results<Nothing>()
    object Empty : Results<Nothing>()
    data class Success<out T>(val data: T) : Results<T>()
    data class Error(val cause: HttpResult = HttpResult.NOT_DEFINED, val code: Int? = null, val errorMessage: String? = null) : Results<Nothing>()
}