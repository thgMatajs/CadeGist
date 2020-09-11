package io.gentalha.code.common.status

import java.lang.Exception

class HandleState<out T>(
    val state: State,
    val data: T? = null,
    val exception: Throwable? = null
) {

    fun handle(
        onLoading: () -> Unit = {},
        onSuccess: (T?) -> Unit = {},
        onError: (Throwable?) -> Unit = {}
    ) {
        when (state) {
            State.LOADING -> onLoading()
            State.SUCCESS -> onSuccess(data)
            State.ERROR -> onError(exception)
        }
    }
}