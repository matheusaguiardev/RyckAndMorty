package com.raiuga.data.model

@kotlinx.serialization.Serializable
internal data class CharacterResponse(
    val info: InfoData?,
    val results: List<CharacterData>?
)
