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

class GetGistsUseCaseTest {

    private val dummyGists = DomainFactoryData.dummyGists
    private val repository: GistRepository = mock {
        on { getGists(any()) } doReturn Single.just(dummyGists)
    }
    private val postExecutionThread: PostExecutionThread = mock()
    private val useCase: GetGistsUseCase = GetGistsUseCase(repository, postExecutionThread)

    @Test
    fun `Check if Gists is loaded`() {
        val call = useCase.buildUseCaseSingle(any()).test()
        call.assertValue {
            it.size == dummyGists.size
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