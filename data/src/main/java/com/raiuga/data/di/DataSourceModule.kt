package com.raiuga.data.di

import com.raiuga.data.repository.CharacterDataSource
import com.raiuga.data.repository.CharacterFilteredDataSource
import com.raiuga.domain.repository.CharacterFilteredUseCase
import com.raiuga.domain.repository.CharactersUseCase
import org.koin.dsl.module

val DataSourceModule = module {
    single<CharactersUseCase> { CharacterDataSource(get()) }
    single<CharacterFilteredUseCase> { CharacterFilteredDataSource(get()) }
}