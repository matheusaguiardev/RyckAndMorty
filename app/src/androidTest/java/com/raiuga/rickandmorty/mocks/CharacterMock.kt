package com.raiuga.rickandmorty.mocks

import com.raiuga.domain.model.CharacterInfo
import com.raiuga.domain.model.CharacterResponse
import com.raiuga.domain.model.Info
import com.raiuga.domain.model.Location
import com.raiuga.domain.model.Origin

val CharacterInfoMock = CharacterInfo(
    id = 0,
    name = "Rick",
    status = "Alive",
    species = "Human",
    type = "Teacher",
    gender = "Male",
    origin = Origin(name = "Earth", url = "www.earth.com.br"),
    location = Location(name = "EUA", url = "www.eua.com.br"),
    image = "https://rickandmortyapi.com/api/character/avatar/20.jpeg",
    episode = listOf("episodeOne", "episodeTwo", "episodeThree"),
    url = "www.urlrick.com.br",
    created = "2022/10/12"
)

val CharactersMock = CharacterResponse(
    info = Info(10, 100, "next", "previous"),
    results = listOf(CharacterInfoMock)
)
