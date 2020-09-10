package io.gentalha.code.cadegist.presentation.pagination

import androidx.paging.rxjava2.RxPagingSource
import io.gentalha.code.cadegist.domain.usecase.GetGistsUseCase
import io.gentalha.code.cadegist.model.Gist
import io.reactivex.Single

class GistDataSource(
    private val useCase: GetGistsUseCase
) : RxPagingSource<Int, Gist>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Gist>> {
        val currentLoadingPageKey = params.key ?: 1
        val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
//        return repository.getGists(currentLoadingPageKey)
//            .subscribeOn(Schedulers.io())
//            .map {
//                LoadResult.Page(
//                    data = it,
//                    prevKey = prevKey,
//                    nextKey = currentLoadingPageKey.plus(1)
//                )
//            }

        return Single.create { emitter ->
            useCase.execute(
                params.key ?: 1,
                onSuccess = {
                    emitter.onSuccess(
                        LoadResult.Page(
                            data = it,
                            prevKey = prevKey,
                            nextKey = currentLoadingPageKey.plus(1)
                        )
                    )
                },
                onError = {
                    emitter.onError(it)
                }
            )
        }

    }

}