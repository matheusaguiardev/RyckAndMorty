package com.raiuga.rmcomponent.model

sealed interface StateScreen

object SuccessStateScreen: StateScreen
object LoadingStateScreen: StateScreen
object ErrorStateScreen: StateScreen