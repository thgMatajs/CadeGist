package io.gentalha.code.cadegist.domain.usecase

import io.gentalha.code.cadegist.domain.repository.GistRepository
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.common.exceptions.ParamsCannotBeNullException
import io.gentalha.code.common.rx.PostExecutionThread
import io.gentalha.code.common.rx.SingleUseCase
import io.reactivex.Single

class GetGistDetailUseCase(
    private val repository: GistRepository,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Int, Gist>(postExecutionThread) {

    override fun buildUseCaseSingle(params: Int?): Single<Gist> {
        return params?.let {
            repository.getGistDetail(params)
        } ?: Single.error(ParamsCannotBeNullException())
    }

}