package com.raiuga.data.repository

import com.raiuga.data.mapper.toDomain
import com.raiuga.data.remote.CharactersRemote
import com.raiuga.domain.model.CharacterInfo
import com.raiuga.domain.model.Outcome
import com.raiuga.domain.repository.CharactersUseCase
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.max

private const val ERROR_MESSAGE = "Falha em buscar personagens na API"
private const val ERROR_PAGE_MESSAGE = "Página não encontrada"

internal class CharacterDataSource(
    private val api: CharactersRemote
) : CharactersUseCase() {
    override fun fetchCharacterList(page: Int): Flow<Outcome<List<CharacterInfo>>> {
        return flow {
            maxPage?.let {
                if (it >= page) {
                    return@flow emit(Outcome.Error(Exception(ERROR_PAGE_MESSAGE)))
                }
            }
            val result = api.fetchCharactersList(page)

            if(maxPage != null) {
                maxPage = result.body()?.info?.pages
            }

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