package com.raiuga.rickandmorty.mocks.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.raiuga.data.remote.CharactersRemote
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
val remoteModuleTest = module {
    single { createOkHttpClient() }
    single { createWebService<CharactersRemote>(get()) }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

@ExperimentalSerializationApi
inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient
): T {
    val contentType = "application/json".toMediaType()
    val converterFactory = Json.asConverterFactory(contentType)

    val retrofit = Retrofit.Builder()
        .baseUrl("https://127.0.0.1:8080/")
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}
