package com.sanlean.totem

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
