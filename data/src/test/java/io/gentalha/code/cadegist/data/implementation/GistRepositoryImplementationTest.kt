package io.gentalha.code.cadegist.data.implementation

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.gentalha.code.cadegist.data.factory.DataFactoryData
import io.gentalha.code.cadegist.data.repository.GistCacheRepository
import io.gentalha.code.cadegist.data.repository.GistRemoteRepository
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Test

class GistRepositoryImplementationTest {


    private val dummyGist = DataFactoryData.dummyDataGist
    private val dummyGists = DataFactoryData.dummyDataGists

    private val gistCache: GistCacheRepository = mock {
        on { addGistInFavorite(dummyGist) } doReturn Completable.complete()
        on { removeGistInFavorite(dummyGist) } doReturn Completable.complete()
        on { getFavoriteGists() } doReturn Single.just(dummyGists)
    }
    private val gistRemote: GistRemoteRepository = mock {
        on { getGists(any()) } doReturn Single.just(dummyGists)
        on { getGistDetail("123") } doReturn Single.just(dummyGist)
    }
    private val repositoryImplementation = GistRepositoryImplementation(gistCache, gistRemote)


    @Test
    fun `Check if the Gist is add in favorite cache list`() {
        val test = repositoryImplementation.addGistInFavorite(dummyGist).test()
        test.assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `Check if the Gist is removed in favorite cache list`() {
        val test = repositoryImplementation.removeGistInFavorite(dummyGist).test()
        test.assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `Check that the favorites Gists cache list is loading`() {
        val test = repositoryImplementation.getFavoriteGists().test()
        test.assertNoErrors()
            .assertComplete()
            .assertValue {
                it.size == dummyGists.size
            }
    }

    @Test
    fun `Check that the gist list is loading`() {
        val test = repositoryImplementation.getGists(any()).test()
        test.awaitTerminalEvent()
        test.assertNoErrors()
            .assertComplete()
            .assertValue {
                it.size == dummyGists.size
            }
    }

    @Test
    fun `Check that the gist detail is loading`() {
        val test = repositoryImplementation.getGistDetail("123").test()
        test.assertNoErrors()
            .assertComplete()
            .assertValue {
                it == dummyGist
            }
    }


}