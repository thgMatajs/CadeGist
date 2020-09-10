package io.gentalha.code.cadegist.domain.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.gentalha.code.cadegist.domain.factory.DomainFactoryData
import io.gentalha.code.cadegist.domain.repository.GistRepository
import io.gentalha.code.common.rx.PostExecutionThread
import io.reactivex.Single
import org.junit.Test

class GetFavoriteGistsUseCaseTest {

    private val dummyGists = DomainFactoryData.dummyGists
    private val repository: GistRepository = mock {
        on { getFavoriteGists() } doReturn Single.just(dummyGists)
    }
    private val postExecutionThread: PostExecutionThread = mock()
    private val useCase: GetFavoriteGistsUseCase = GetFavoriteGistsUseCase(repository, postExecutionThread)

    @Test
    fun `Check if Favorite Gists is loaded`() {
        val call = useCase.buildUseCaseSingle().test()
        call.assertValue {
            it.size == dummyGists.size
        }
            .assertNoErrors()
            .assertComplete()
    }

}