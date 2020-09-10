package io.gentalha.code.cadegist.presentation.di

import io.gentalha.code.cadegist.presentation.pagination.GistDataSource
import io.gentalha.code.cadegist.presentation.rx.UiThread
import io.gentalha.code.cadegist.presentation.viewmodel.*
import io.gentalha.code.common.rx.PostExecutionThread
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module(override = true) {
    single<PostExecutionThread> { UiThread() }
    factory { GistDataSource(get()) }
    viewModel { GetGistsViewModel(get()) }
    viewModel { GetFavoriteGistsViewModel(get()) }
    viewModel { GetGistDetailViewModel(get()) }
    viewModel { AddGistInFavoriteViewModel(get()) }
    viewModel { RemoveGistInFavoriteViewModel(get()) }
}