package io.gentalha.code.cadegist.data.factory

import com.nhaarman.mockitokotlin2.mock
import io.gentalha.code.cadegist.model.Gist

object DataFactoryData {


    val dummyDataGist: Gist = mock()


    val dummyDataGists = listOf(
        dummyDataGist,
        dummyDataGist,
        dummyDataGist
    )
}