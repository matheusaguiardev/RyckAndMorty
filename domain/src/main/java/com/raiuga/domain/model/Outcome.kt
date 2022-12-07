package com.raiuga.domain.model

sealed class Outcome<T>(
    val data: T? = null,
    val error: Exception? = null
) {
    class Success<T>(data: T?): Outcome<T>(data = data)
    class Error<T>(error: Exception): Outcome<T>(error = error)
}
