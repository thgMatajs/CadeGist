package io.gentalha.code.cadegist.cache.implementation

import io.gentalha.code.cadegist.cache.dao.GistDao
import io.gentalha.code.cadegist.cache.entities.toCache
import io.gentalha.code.cadegist.data.repository.GistCacheRepository
import io.gentalha.code.cadegist.model.Gist
import io.reactivex.Completable
import io.reactivex.Single

class GistCacheRepositoryImplementation(
    private val gistDao: GistDao
) : GistCacheRepository {


    override fun getFavoriteGists(): Single<List<Gist>> {
        return gistDao.getFavoriteGists().map { gistList ->
            gistList.map { it }
        }
    }

    override fun addGistInFavorite(gist: Gist): Completable {
        return gistDao.insertInFavorite(gist.toCache())
    }

    override fun removeGistInFavorite(gist: Gist): Completable {
        return gistDao.removeInFavorite(gist.id)
    }
}