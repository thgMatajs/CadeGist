package io.gentalha.code.cadegist.data.di

import io.gentalha.code.cadegist.data.implementation.GistRepositoryImplementation
import io.gentalha.code.cadegist.domain.repository.GistRepository
import org.koin.dsl.module

val dataModule = module(override = true) {
    factory<GistRepository> { GistRepositoryImplementation(get(), get()) }
}