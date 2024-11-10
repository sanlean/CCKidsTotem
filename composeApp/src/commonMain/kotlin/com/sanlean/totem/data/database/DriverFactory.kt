package com.sanlean.totem.data.database

import app.cash.sqldelight.db.SqlDriver

expect object DriverFactory {
    fun createDriver(): SqlDriver
}
