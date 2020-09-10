package io.gentalha.code.cadegist.cache.di

import io.gentalha.code.cadegist.cache.dao.GistDao
import io.gentalha.code.cadegist.cache.database.GistDatabase
import io.gentalha.code.cadegist.cache.implementation.GistCacheRepositoryImplementation
import io.gentalha.code.cadegist.data.repository.GistCacheRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheModule = module(override = true) {
    factory<GistDao> { GistDatabase.getDatabase(androidContext()).gistDao() }
    factory<GistCacheRepository> { GistCacheRepositoryImplementation(get()) }
}