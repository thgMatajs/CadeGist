package io.gentalha.code.cadegist.data.repository

import io.gentalha.code.cadegist.model.Gist
import io.reactivex.Completable
import io.reactivex.Single

interface GistCacheRepository {

    fun getFavoriteGists(): Single<List<Gist>>

    fun addGistInFavorite(gist: Gist): Completable

    fun removeGistInFavorite(gist: Gist): Completable
}