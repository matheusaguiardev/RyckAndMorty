package com.raiuga.rickandmorty.app

import com.raiuga.rickandmorty.viewmodel.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModules = module {
    viewModel { CharacterViewModel(get(), get()) }
}
