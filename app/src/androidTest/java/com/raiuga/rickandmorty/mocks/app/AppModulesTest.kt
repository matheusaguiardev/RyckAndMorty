package com.raiuga.rickandmorty.mocks.app

import com.raiuga.data.repository.CharacterDataSource
import com.raiuga.data.repository.CharacterFilteredDataSource
import com.raiuga.domain.repository.CharacterFilteredUseCase
import com.raiuga.domain.repository.CharactersUseCase
import com.raiuga.rickandmorty.mocks.remote.remoteModuleTest
import com.raiuga.rickandmorty.viewmodel.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val DataSourceModule = module {
    single<CharactersUseCase> { CharacterDataSource(get()) }
    single<CharacterFilteredUseCase> { CharacterFilteredDataSource(get()) }
}

val ViewModelModules = module {
    viewModel { CharacterViewModel(get(), get()) }
}

val appModulesTest = listOf(
    remoteModuleTest,
    DataSourceModule,
    ViewModelModules
)
