package io.gentalha.code.cadegist.di

import io.gentalha.code.cadegist.cache.di.cacheModule
import io.gentalha.code.cadegist.data.di.dataModule
import io.gentalha.code.cadegist.domain.di.domainModule
import io.gentalha.code.cadegist.presentation.di.presentationModule
import io.gentalha.code.cadegist.remote.di.remoteModule

val allModules = listOf(
    domainModule,
    dataModule,
    remoteModule,
    cacheModule,
    presentationModule
)