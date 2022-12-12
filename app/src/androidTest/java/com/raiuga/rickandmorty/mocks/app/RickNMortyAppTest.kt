package com.raiuga.rickandmorty.mocks.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickNMortyAppTest : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModulesTest)
            androidContext(this@RickNMortyAppTest)
        }
    }
}
