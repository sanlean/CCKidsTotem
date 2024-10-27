package com.sanlean.totem.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.*

actual object ClassClient {
    actual val client: HttpClient
        get() = HttpClient(OkHttp){
            engine {
                config {
                    followRedirects(true)
                }
            }
        }

}