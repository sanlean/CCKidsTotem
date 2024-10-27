package com.sanlean.totem.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java

actual object ClassClient {
    actual val client: HttpClient
        get() = HttpClient(Java){
            engine {
                protocolVersion = java.net.http.HttpClient.Version.HTTP_2
            }
        }

}