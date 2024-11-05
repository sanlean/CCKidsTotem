package com.sanlean.totem.data.api

import io.ktor.client.*

expect object ClientFactory {

    val client: HttpClient
}