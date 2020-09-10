package io.gentalha.code.cadegist.data.di

import io.gentalha.code.cadegist.data.implementation.GistRepositoryImplementation
import org.koin.dsl.module

val dataModule = module {
    factory { GistRepositoryImplementation(get(), get()) }
}