package com.sanlean.totem.data.api

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

actual object ClientFactory {
    actual val client: HttpClient
        get() = HttpClient(OkHttp) {
            engine {
                config {
                    followRedirects(true)
                }
            }
        }

}