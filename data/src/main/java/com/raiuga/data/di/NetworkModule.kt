package com.raiuga.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.raiuga.data.BuildConfig
import com.raiuga.data.remote.CharactersRemote
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalSerializationApi::class)
val NetworkModule = module {
    single { createOkHttpClient() }
    single { createWebService<CharactersRemote>(get()) }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

@ExperimentalSerializationApi
inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    val contentType = "application/json".toMediaType()
    val converterFactory = Json.asConverterFactory(contentType)

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}
