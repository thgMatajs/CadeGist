package io.gentalha.code.cadegist.presentation.pagination

import androidx.paging.rxjava2.RxPagingSource
import io.gentalha.code.cadegist.domain.repository.GistRepository
import io.gentalha.code.cadegist.model.Gist
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GistDataSource(
    private val repository: GistRepository
) : RxPagingSource<Int, Gist>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Gist>> {
        val currentLoadingPageKey = params.key ?: 1
        return repository.getGists(currentLoadingPageKey)
            .subscribeOn(Schedulers.io())
            .map {
                loadResult(it, currentLoadingPageKey)
            }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun loadResult(data: List<Gist>, currentLoadingPageKey: Int): LoadResult<Int, Gist> {
        val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
        return LoadResult.Page(
            data = data,
            prevKey = prevKey,
            nextKey = currentLoadingPageKey.plus(1)
        )
    }

}