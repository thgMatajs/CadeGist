package io.gentalha.code.cadegist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.gentalha.code.cadegist.domain.usecase.GetFavoriteGistsUseCase
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.cadegist.presentation.extensions.setFailure
import io.gentalha.code.cadegist.presentation.extensions.setLoading
import io.gentalha.code.cadegist.presentation.extensions.setSuccess
import io.gentalha.code.common.status.HandleState

class GetFavoriteGistsViewModel(
    private val useCase: GetFavoriteGistsUseCase
) : ViewModel() {

    val favoriteGistLiveData: MutableLiveData<HandleState<List<Gist>>> by lazy {
        MutableLiveData<HandleState<List<Gist>>>()
    }

    fun loadFavoriteGists() {
        favoriteGistLiveData.setLoading()
        useCase.execute(
            onSuccess = {
                favoriteGistLiveData.setSuccess(it)
            },
            onError = {
                favoriteGistLiveData.setFailure(it)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        useCase.dispose()
    }
}