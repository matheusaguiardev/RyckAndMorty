package com.raiuga.data.repository

import com.raiuga.data.remote.CharactersRemote
import com.raiuga.domain.model.CharacterInfo
import com.raiuga.domain.model.Outcome
import com.raiuga.domain.repository.CharacterFilteredUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

private const val ERROR_MESSAGE = "Nenhum resultado encontrado pelo filtro"

class CharacterFilteredDataSource(
    private val api: CharactersRemote
): CharacterFilteredUseCase() {
    override fun fetchCharacter(
        name: String,
        status: String
    ): Flow<Outcome<List<CharacterInfo>>> {
        return flow {
            val result = api.filterCharacters(name = name, status = status)
            emit(
                if (result.isSuccessful) {
                    Outcome.Success(result.body()?.results)
                } else {
                    Outcome.Error(IOException(ERROR_MESSAGE))
                }
            )
        }
    }
}