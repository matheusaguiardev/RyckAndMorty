package com.raiuga.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class InfoData(
    val count: Int?,
    val pages: Int?,
    val next: String?,
    val prev: String?
)
