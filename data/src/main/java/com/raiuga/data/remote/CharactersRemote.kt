package com.raiuga.data.remote

import com.raiuga.data.model.CharacterResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CharactersRemote {

    @GET("character")
    suspend fun fetchCharactersList(
        @Query("page") page: Int
    ): Response<CharacterResponse>

    @GET("character")
    suspend fun filterCharacters(
        @Query("name") name: String = "",
        @Query("status") status: String = "",
        @Query("species") species: String = "",
        @Query("type") type: String = "",
        @Query("gender") gender: String = ""
    ): Response<CharacterResponse>

}