package io.gentalha.code.cadegist.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.gentalha.code.cadegist.cache.entities.CacheGist
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInFavorite(gist: CacheGist): Completable

    @Query("DELETE FROM gists WHERE id = :gistId")
    fun removeInFavorite(gistId: String): Completable

    @Query("SELECT * FROM gists")
    fun getFavoriteGists(): Single<List<CacheGist>>
}