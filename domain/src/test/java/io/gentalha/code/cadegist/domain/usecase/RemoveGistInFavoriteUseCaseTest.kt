package io.gentalha.code.cadegist.domain.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.gentalha.code.cadegist.domain.factory.DomainFactoryData
import io.gentalha.code.cadegist.domain.repository.GistRepository
import io.gentalha.code.common.exceptions.ParamsCannotBeNullException
import io.gentalha.code.common.rx.PostExecutionThread
import io.reactivex.Completable
import org.junit.Test

class RemoveGistInFavoriteUseCaseTest {

    private val repository: GistRepository = mock {
        on { removeGistInFavorite(DomainFactoryData.dummyGist) } doReturn Completable.complete()
    }
    private val postExecutionThread: PostExecutionThread = mock()
    private val useCase: RemoveGistInFavoriteUseCase =
        RemoveGistInFavoriteUseCase(repository, postExecutionThread)

    @Test
    fun `Check if remove Gist in favorite is success`() {
        val call = useCase.buildUseCaseCompletable(DomainFactoryData.dummyGist).test()
        call.assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `Check exception when pass null param`() {
        val call = useCase.buildUseCaseCompletable(null).test()
        call.assertFailure(ParamsCannotBeNullException::class.java)
    }
}