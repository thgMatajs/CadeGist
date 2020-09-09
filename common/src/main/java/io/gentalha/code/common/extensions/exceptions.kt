package io.gentalha.code.common.extensions

import retrofit2.HttpException
import java.net.UnknownHostException
import kotlin.Exception

fun Exception.handleExceptions(
    httpException: () -> Unit = {},
    whitOutNetWorkException: () -> Unit = {},
    otherExceptions: (error: Exception) -> Unit = {}
) {

    when (this) {
        is HttpException -> httpException.invoke()
        is UnknownHostException -> whitOutNetWorkException.invoke()
        else -> otherExceptions.invoke(this)
    }

}