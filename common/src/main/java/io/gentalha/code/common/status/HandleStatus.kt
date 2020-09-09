package io.gentalha.code.common.status

import java.lang.Exception

class HandleStatus<out T>(
    val status: Status,
    val data: T?,
    val exception: Exception?
) {

    companion object {
        fun <T> loading(): HandleStatus<T> {
            return HandleStatus(Status.LOADING, null, null)
        }

        fun <T> success(data: T?): HandleStatus<T> {
            return HandleStatus(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, ex: Exception): HandleStatus<T> {
            return HandleStatus(Status.ERROR, null, ex)
        }
    }
}