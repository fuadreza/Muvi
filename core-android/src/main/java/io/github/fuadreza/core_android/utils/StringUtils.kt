package io.github.fuadreza.core_android.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.toLocalDateFormat(): String {
    val format = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US
    )
    val simpleFormat = SimpleDateFormat(
        "yyyy-MM-dd", Locale.US
    )
    format.timeZone = TimeZone.getTimeZone("UTC")
    val date = format.parse(this)
    return simpleFormat.format(date!!)
}