package com.raiuga.data.repository

import com.raiuga.data.mapper.toDomain
import com.raiuga.data.remote.CharactersRemote
import com.raiuga.domain.model.CharacterList
import com.raiuga.domain.model.Outcome
import com.raiuga.domain.repository.CharacterFilteredUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

private const val ERROR_MESSAGE = "Nenhum resultado encontrado pelo filtro"

internal class CharacterFilteredDataSource(
    private val api: CharactersRemote
): CharacterFilteredUseCase() {
    override fun fetchCharacter(
        name: String,
        status: String
    ): Flow<Outcome<CharacterList>> {
        return flow {
            val result = api.filterCharacters(name = name, status = status)
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