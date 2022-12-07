package com.raiuga.rickandmorty.app

import android.app.Application
import com.raiuga.rickandmorty.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class RickNMortyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickNMortyApplication)
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR)
            modules(AppModules)
        }
    }

}