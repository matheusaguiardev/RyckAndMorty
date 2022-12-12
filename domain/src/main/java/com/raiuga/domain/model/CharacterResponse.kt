package com.raiuga.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val info: Info?,
    val results: List<CharacterInfo>?
)