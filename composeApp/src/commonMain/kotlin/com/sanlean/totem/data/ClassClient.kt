package com.sanlean.totem.data

import io.ktor.client.*

expect object ClassClient {

    val client: HttpClient
}