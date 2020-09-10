package io.gentalha.code.cadegist.domain.repository

import io.gentalha.code.cadegist.model.Gist
import io.reactivex.Completable
import io.reactivex.Single

interface GistRepository {

    fun getGists(page: Int): Single<List<Gist>>

    fun getFavoriteGists(): Single<List<Gist>>

    fun getGistDetail(gistId: String): Single<Gist>

    fun addGistInFavorite(gist: Gist): Completable

    fun removeGistInFavorite(gist: Gist): Completable

}