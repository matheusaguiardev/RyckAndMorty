package com.raiuga.domain.repository

import com.raiuga.domain.model.CharacterInfo
import com.raiuga.domain.model.Outcome
import kotlinx.coroutines.flow.Flow

abstract class CharactersUseCase {
    var currentPage: Int = 1
    var maxPage: Int? = null

    abstract fun fetchCharacterList(page: Int = currentPage): Flow<Outcome<List<CharacterInfo>>>
}