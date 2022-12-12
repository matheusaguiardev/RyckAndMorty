package com.raiuga.rickandmorty.mocks.remote

import com.raiuga.rickandmorty.mocks.CharactersMock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

open class ApiMock {
    private val server = MockWebServer()
    private val serverPort = 8080

    val successResponse by lazy {
        val body = Json.Default.encodeToString(CharactersMock)
        MockResponse()
            .setResponseCode(200)
            .setBody(body)
    }

    private val errorResponse by lazy { MockResponse().setResponseCode(404) }

    open fun setUp() {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "character" -> successResponse
                    else -> errorResponse
                }
            }
        }
        server.start(serverPort)
    }

    open fun turnOff() {
        server.close()
    }
}
