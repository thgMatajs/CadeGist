package io.gentalha.code.cadegist.domain.di

import io.gentalha.code.cadegist.domain.usecase.*
import org.koin.dsl.module

val domainModule = module(override = true) {
    factory { GetGistsUseCase(get(), get()) }
    factory { GetFavoriteGistsUseCase(get(), get()) }
    factory { GetGistDetailUseCase(get(), get()) }
    factory { AddGistInFavoriteUseCase(get(), get()) }
    factory { RemoveGistInFavoriteUseCase(get(), get()) }
}