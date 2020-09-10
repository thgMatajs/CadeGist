package io.gentalha.code.cadegist.cache.di

import io.gentalha.code.cadegist.cache.database.GistDatabase
import io.gentalha.code.cadegist.cache.implementation.GistCacheRepositoryImplementation
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheModule = module {
    factory { GistDatabase.getDatabase(androidContext()).gistDao() }
    factory { GistCacheRepositoryImplementation(get()) }
}