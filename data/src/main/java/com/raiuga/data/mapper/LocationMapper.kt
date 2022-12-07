package com.raiuga.data.mapper

import com.raiuga.data.model.LocationData
import com.raiuga.domain.model.Location

internal fun LocationData.toDomain() = Location(
    name = this.name.orEmpty(),
    url = this.url.orEmpty()
)