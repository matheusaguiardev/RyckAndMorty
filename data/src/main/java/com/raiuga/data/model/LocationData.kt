package com.raiuga.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class LocationData(
    val name: String? = "",
    val url: String? = ""
)
