package io.gentalha.code.cadegist.domain.usecase

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.gentalha.code.cadegist.domain.factory.DomainFactoryData
import io.gentalha.code.cadegist.domain.repository.GistRepository
import io.gentalha.code.common.exceptions.ParamsCannotBeNullException
import io.gentalha.code.common.rx.PostExecutionThread
import io.reactivex.Single
import org.junit.Test

class GetGistDetailUseCaseTest {

    private val repository: GistRepository = mock {
        on { getGistDetail(any()) } doReturn Single.just(DomainFactoryData.dummyGist)
    }
    private val postExecutionThread: PostExecutionThread = mock()
    private val useCase: GetGistDetailUseCase =
        GetGistDetailUseCase(repository, postExecutionThread)

    @Test
    fun `Check if Gist Detail is loaded`() {
        val call = useCase.buildUseCaseSingle(any()).test()

        call.assertValue {
            it == DomainFactoryData.dummyGist
        }
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `Check exception when pass null param`() {
        val call = useCase.buildUseCaseSingle(null).test()
        call.assertFailure(ParamsCannotBeNullException::class.java)
    }
}