package com.sanlean.totem.data.api

import io.ktor.client.*
import io.ktor.client.engine.java.*
import java.net.http.HttpClient

actual object ClientFactory {
    actual val client: io.ktor.client.HttpClient
        get() = HttpClient(Java) {
            engine {
                protocolVersion = HttpClient.Version.HTTP_2
            }
        }
}
