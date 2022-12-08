package com.raiuga.domain.repository

import com.raiuga.domain.model.CharacterInfo
import com.raiuga.domain.model.Outcome
import kotlinx.coroutines.flow.Flow

abstract class CharacterFilteredUseCase {

    abstract fun fetchCharacter(
        name: String = "",
        status: String = ""
    ): Flow<Outcome<List<CharacterInfo>>>

}