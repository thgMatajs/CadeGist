package io.gentalha.code.cadegist.application

import android.app.Application
import io.gentalha.code.cadegist.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class CadeGistApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@CadeGistApplication)
            androidLogger()
            loadKoinModules(allModules)
        }
    }
}