package io.gentalha.code.cadegist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.gentalha.code.cadegist.domain.usecase.AddGistInFavoriteUseCase
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.cadegist.presentation.extensions.setFailure
import io.gentalha.code.cadegist.presentation.extensions.setLoading
import io.gentalha.code.cadegist.presentation.extensions.setSuccess
import io.gentalha.code.common.status.HandleState

class AddGistInFavoriteViewModel(
    private val useCase: AddGistInFavoriteUseCase
) : ViewModel() {

    val addFavoriteGistLiveData: MutableLiveData<HandleState<Unit>> by lazy {
        MutableLiveData<HandleState<Unit>>()
    }

    fun addInFavorite(gist: Gist) {
        addFavoriteGistLiveData.setLoading()
        useCase.execute(
            gist,
            onSuccess = {
                addFavoriteGistLiveData.setSuccess()
            },
            onError = {
                addFavoriteGistLiveData.setFailure(it)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        useCase.dispose()
    }
}