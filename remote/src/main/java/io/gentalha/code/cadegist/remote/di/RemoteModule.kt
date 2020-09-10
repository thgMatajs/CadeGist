package io.gentalha.code.cadegist.remote.di

import io.gentalha.code.cadegist.data.repository.GistRemoteRepository
import io.gentalha.code.cadegist.remote.implementation.GistRemoteRepositoryImplementation
import io.gentalha.code.cadegist.remote.service.GistService
import io.gentalha.code.common.network.ServiceBuilder
import org.koin.dsl.module

val remoteModule = module {
    factory { ServiceBuilder().invoke<GistService>("https://api.github.com/") }
    factory<GistRemoteRepository> { GistRemoteRepositoryImplementation(get()) }
}