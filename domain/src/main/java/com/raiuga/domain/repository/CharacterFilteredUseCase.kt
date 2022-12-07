package com.raiuga.domain.repository

import com.raiuga.domain.model.CharacterList
import com.raiuga.domain.model.Outcome
import kotlinx.coroutines.flow.Flow

abstract class CharacterFilteredUseCase {

    abstract fun fetchCharacter(
        name: String = "",
        status: String = ""
    ): Flow<Outcome<CharacterList>>

}