package com.raiuga.rickandmorty.app

import com.raiuga.data.di.DataSourceModule
import com.raiuga.data.di.NetworkModule

val AppModules = listOf(
        NetworkModule,
        DataSourceModule,
        ViewModelModules
    )