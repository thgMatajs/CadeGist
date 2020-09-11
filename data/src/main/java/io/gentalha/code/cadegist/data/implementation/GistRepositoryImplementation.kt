package io.gentalha.code.cadegist.data.implementation

import io.gentalha.code.cadegist.data.repository.GistCacheRepository
import io.gentalha.code.cadegist.data.repository.GistRemoteRepository
import io.gentalha.code.cadegist.domain.repository.GistRepository
import io.gentalha.code.cadegist.model.Gist
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GistRepositoryImplementation(
    private val gistCache: GistCacheRepository,
    private val gistRemote: GistRemoteRepository
) : GistRepository {

    override fun getGists(page: Int): Single<List<Gist>> {
        return Single.zip(
            gistRemote.getGists(page).subscribeOn(Schedulers.io()),
            gistCache.getFavoriteGists().subscribeOn(Schedulers.io()),
            { remoteGists, favoriteGists ->
                remoteGists.map { remoteGist ->
                    favoriteGists.map { cacheGist ->
                        remoteGist.isFavorite = remoteGist.id == cacheGist.id
                        println("THG_LOG GIST --> ${remoteGist.owner.name} - ${remoteGist.isFavorite}")
                    }
                }
                remoteGists
            }
        )
    }

    override fun getFavoriteGists(): Single<List<Gist>> {
        return gistCache.getFavoriteGists()
    }

    override fun getGistDetail(gistId: String): Single<Gist> {
        return gistRemote.getGistDetail(gistId)
    }

    override fun addGistInFavorite(gist: Gist): Completable {
        return gistCache.addGistInFavorite(gist)
    }

    override fun removeGistInFavorite(gist: Gist): Completable {
        return gistCache.removeGistInFavorite(gist)
    }
}