package com.raiuga.data.repository

import com.raiuga.data.mapper.toDomain
import com.raiuga.data.remote.CharactersRemote
import com.raiuga.domain.model.CharacterList
import com.raiuga.domain.model.Outcome
import com.raiuga.domain.repository.CharactersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

private const val ERROR_MESSAGE = "Falha em buscar personagens na API"

internal class CharacterDataSource(
    private val api: CharactersRemote
): CharactersUseCase() {
    override fun fetchCharacterList(
        page: Int
    ): Flow<Outcome<CharacterList>> {
        return flow {
            val result = api.fetchCharactersList(page)
            emit(
                if (result.isSuccessful) {
                    Outcome.Success(result.body()?.toDomain())
                } else {
                    Outcome.Error(IOException(ERROR_MESSAGE))
                }
            )
        }
    }
}