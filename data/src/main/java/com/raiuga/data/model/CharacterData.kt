package com.raiuga.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CharacterData(
    val id: Int? = -1,
    val name: String? = "",
    val status: String? = "",
    val species: String? = "",
    val type: String? = "",
    val gender: String? = "",
    val origin: OriginData = OriginData(),
    val location: LocationData = LocationData(),
    val image: String? = "",
    val episode: List<String>? = emptyList(),
    val url: String? = "",
    val created: String? = ""
)
