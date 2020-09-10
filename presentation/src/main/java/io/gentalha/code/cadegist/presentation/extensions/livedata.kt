package io.gentalha.code.cadegist.presentation.extensions

import androidx.lifecycle.MutableLiveData
import io.gentalha.code.common.status.HandleState
import io.gentalha.code.common.status.State
import java.lang.Exception

fun <T> MutableLiveData<HandleState<T>>.setLoading() {
    postValue(HandleState(State.LOADING))
}

fun <T> MutableLiveData<HandleState<T>>.setSuccess(data: T? = null) {
    postValue(HandleState(State.SUCCESS, data = data))
}

fun <T> MutableLiveData<HandleState<T>>.setFailure(error: Exception) {
    postValue(HandleState(State.ERROR, exception = error))
}