package io.gentalha.code.cadegist.remote.service

import io.gentalha.code.cadegist.remote.model.RemoteGist
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GistService {


    @GET("gists/public")
    fun getGists(
        @Query("page") page: Int
    ): Single<List<RemoteGist>>

    @GET("/gists/{gist_id}")
    fun getGistDetail(
        @Path("gist_id") gistId: Int
    ): Single<RemoteGist>
}