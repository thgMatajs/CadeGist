package io.gentalha.code.cadegist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.gentalha.code.cadegist.domain.usecase.RemoveGistInFavoriteUseCase
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.cadegist.presentation.extensions.setFailure
import io.gentalha.code.cadegist.presentation.extensions.setLoading
import io.gentalha.code.cadegist.presentation.extensions.setSuccess
import io.gentalha.code.common.status.HandleState

class RemoveGistInFavoriteViewModel(
    private val useCase: RemoveGistInFavoriteUseCase
) : ViewModel() {

    val removeFavoriteGistLiveData: MutableLiveData<HandleState<Unit>> by lazy {
        MutableLiveData<HandleState<Unit>>()
    }

    fun removeOfFavorite(gist: Gist) {
        removeFavoriteGistLiveData.setLoading()
        useCase.execute(
            gist,
            onSuccess = {
                removeFavoriteGistLiveData.setSuccess()
            },
            onError = {
                removeFavoriteGistLiveData.setFailure(it)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        useCase.dispose()
    }
}