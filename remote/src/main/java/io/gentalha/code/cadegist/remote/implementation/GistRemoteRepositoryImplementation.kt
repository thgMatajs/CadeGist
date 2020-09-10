package io.gentalha.code.cadegist.remote.implementation

import io.gentalha.code.cadegist.data.repository.GistRemoteRepository
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.cadegist.remote.service.GistService
import io.reactivex.Single

class GistRemoteRepositoryImplementation(
    private val service: GistService
) : GistRemoteRepository {

    override fun getGists(page: Int): Single<List<Gist>> {
        return service.getGists(page).map { gists ->
            gists.map { it }
        }
    }

    override fun getGistDetail(gistId: Int): Single<Gist> {
        return service.getGistDetail(gistId).map { it }
    }
}