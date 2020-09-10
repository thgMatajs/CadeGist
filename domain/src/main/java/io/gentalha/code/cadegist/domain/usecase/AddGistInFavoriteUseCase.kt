package io.gentalha.code.cadegist.domain.usecase

import io.gentalha.code.cadegist.domain.repository.GistRepository
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.common.exceptions.ParamsCannotBeNullException
import io.gentalha.code.common.rx.CompletableUseCase
import io.gentalha.code.common.rx.PostExecutionThread
import io.reactivex.Completable

class AddGistInFavoriteUseCase(
    private val repository: GistRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<Gist>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Gist?): Completable {
        return params?.let {
            repository.addGistInFavorite(params)
        } ?: Completable.error(ParamsCannotBeNullException())
    }

}