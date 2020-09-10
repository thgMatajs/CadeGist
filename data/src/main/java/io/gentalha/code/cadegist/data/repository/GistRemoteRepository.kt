package io.gentalha.code.cadegist.data.repository

import io.gentalha.code.cadegist.model.Gist
import io.reactivex.Single

interface GistRemoteRepository {

    fun getGists(page: Int): Single<List<Gist>>

    fun getGistDetail(gistId: Int): Single<Gist>

}