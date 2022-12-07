package com.raiuga.data.mapper

import com.raiuga.data.model.OriginData
import com.raiuga.domain.model.Origin

internal fun OriginData.toDomain() = Origin(
    name = this.name.orEmpty(),
    url = this.url.orEmpty()
)