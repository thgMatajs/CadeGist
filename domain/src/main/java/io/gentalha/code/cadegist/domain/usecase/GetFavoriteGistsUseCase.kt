package io.gentalha.code.cadegist.domain.usecase

import io.gentalha.code.cadegist.domain.repository.GistRepository
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.common.rx.PostExecutionThread
import io.gentalha.code.common.rx.SingleUseCase
import io.reactivex.Single

class GetFavoriteGistsUseCase(
    private val repository: GistRepository,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Unit, List<Gist>>(postExecutionThread) {

    override fun buildUseCaseSingle(params: Unit?): Single<List<Gist>> {
        return repository.getFavoriteGists()
    }

}