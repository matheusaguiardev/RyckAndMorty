package com.raiuga.domain.repository

import com.raiuga.domain.model.CharacterInfo
import com.raiuga.domain.model.CharacterList
import com.raiuga.domain.model.Outcome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single

abstract class CharactersUseCase {
    var currentPage: Int = 1
    var charactersList: List<CharacterInfo> = emptyList()

    suspend fun fetchSinglePageAndCache(
        page: Int = currentPage
    ): Outcome<CharacterList> {
        return when (val result = fetchCharacterList(page).single()) {
            is Outcome.Success -> {
                charactersList = charactersList + (result.data?.list ?: emptyList())
                Outcome.Success(CharacterList(charactersList))
            }
            is Outcome.Error -> result
        }
    }

    abstract fun fetchCharacterList(page: Int): Flow<Outcome<CharacterList>>
}