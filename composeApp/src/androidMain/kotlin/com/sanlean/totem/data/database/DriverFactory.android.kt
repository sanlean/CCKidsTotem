package com.sanlean.totem.data.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.sanlean.totem.Application
import com.sanlean.totem.Database

actual object DriverFactory {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, Application.getContext(), "totem.db")
    }
}