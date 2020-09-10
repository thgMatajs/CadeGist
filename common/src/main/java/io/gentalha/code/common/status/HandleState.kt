package io.gentalha.code.common.status

import java.lang.Exception

class HandleState<out T>(
    val state: State,
    val data: T? = null,
    val exception: Exception? = null
) {

    companion object {
        fun <T> loading(): HandleState<T> {
            return HandleState(State.LOADING)
        }

        fun <T> success(data: T?): HandleState<T> {
            return HandleState(State.SUCCESS, data)
        }

        fun <T> error(msg: String, ex: Exception): HandleState<T> {
            return HandleState(state = State.ERROR, exception =  ex)
        }
    }
}