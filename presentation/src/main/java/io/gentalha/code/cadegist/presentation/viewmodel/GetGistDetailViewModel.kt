package io.gentalha.code.cadegist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.gentalha.code.cadegist.domain.usecase.GetGistDetailUseCase
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.cadegist.presentation.extensions.setFailure
import io.gentalha.code.cadegist.presentation.extensions.setLoading
import io.gentalha.code.cadegist.presentation.extensions.setSuccess
import io.gentalha.code.common.status.HandleState

class GetGistDetailViewModel(
    private val useCase: GetGistDetailUseCase
) : ViewModel() {

    val gistDetailLiveData: MutableLiveData<HandleState<Gist>> by lazy {
        MutableLiveData<HandleState<Gist>>()
    }

    fun loadGistDetail(gistId: String) {
        gistDetailLiveData.setLoading()
        useCase.execute(
            params = gistId,
            onSuccess = {
                gistDetailLiveData.setSuccess(it)
            },
            onError = {
                gistDetailLiveData.setFailure(it)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        useCase.dispose()
    }

}