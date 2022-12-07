package com.raiuga.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class OriginData(
    val name: String? = "",
    val url: String? = ""
)
