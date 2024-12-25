package com.d4rk.androidtutorials.data.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {

    private val networkTimeout : Long = 10_000L

    fun createClient() : HttpClient {
        return HttpClient(engineFactory = Android) {
            install(plugin = ContentNegotiation) {
                json(json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(plugin = HttpTimeout) {
                requestTimeoutMillis = networkTimeout
                connectTimeoutMillis = networkTimeout
                socketTimeoutMillis = networkTimeout
            }

            install(plugin = DefaultRequest) {
                contentType(type = ContentType.Application.Json)
                accept(contentType = ContentType.Application.Json)
            }
        }
    }
}