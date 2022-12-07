package com.raiuga.data.mapper

import com.raiuga.data.model.CharacterData
import com.raiuga.data.model.CharacterResponse
import com.raiuga.domain.model.CharacterInfo
import com.raiuga.domain.model.CharacterList

internal fun CharacterData.toDomain() = CharacterInfo(
    id = id ?: -1,
    name = name.orEmpty(),
    status = status.orEmpty(),
    species = species.orEmpty(),
    type = type.orEmpty(),
    gender = gender.orEmpty(),
    origin = origin.toDomain(),
    location = location.toDomain(),
    image = image.orEmpty(),
    episode = episode.orEmpty(),
    url = url.orEmpty(),
    created = created.orEmpty()
)

internal fun CharacterResponse.toDomain() = CharacterList(
    list = this.results?.map { it.toDomain() } ?: emptyList()
)