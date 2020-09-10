package io.gentalha.code.cadegist.data.implementation

import io.gentalha.code.cadegist.data.repository.GistCacheRepository
import io.gentalha.code.cadegist.data.repository.GistRemoteRepository
import io.gentalha.code.cadegist.domain.repository.GistRepository
import io.gentalha.code.cadegist.model.Gist
import io.reactivex.Completable
import io.reactivex.Single

class GistRepositoryImplementation(
    private val gistCache: GistCacheRepository,
    private val gistRemote: GistRemoteRepository
) : GistRepository {

    override fun getGists(page: Int): Single<List<Gist>> {
        return gistRemote.getGists(page)
    }

    override fun getFavoriteGists(): Single<List<Gist>> {
        return gistCache.getFavoriteGists()
    }

    override fun getGistDetail(gistId: Int): Single<Gist> {
        return gistRemote.getGistDetail(gistId)
    }

    override fun addGistInFavorite(gist: Gist): Completable {
        return gistCache.addGistInFavorite(gist)
    }

    override fun removeGistInFavorite(gist: Gist): Completable {
        return gistCache.removeGistInFavorite(gist)
    }
}