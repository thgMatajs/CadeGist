package io.gentalha.code.cadegist.domain.factory

import com.nhaarman.mockitokotlin2.mock
import io.gentalha.code.cadegist.model.Gist

object DomainFactoryData {


    val dummyGist: Gist = mock()


    val dummyGists = listOf(
        dummyGist,
        dummyGist,
        dummyGist
    )
}