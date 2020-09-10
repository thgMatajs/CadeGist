package io.gentalha.code.cadegist.domain.usecase

import io.gentalha.code.cadegist.domain.repository.GistRepository
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.common.exceptions.ParamsCannotBeNullException
import io.gentalha.code.common.rx.PostExecutionThread
import io.gentalha.code.common.rx.SingleUseCase
import io.reactivex.Single

class GetGistsUseCase(
    private val repository: GistRepository,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Int, List<Gist>>(postExecutionThread) {

    override fun buildUseCaseSingle(params: Int?): Single<List<Gist>> {
        return params?.let {
            repository.getGists(params)
        } ?: Single.error(ParamsCannotBeNullException())
    }
}