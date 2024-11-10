package com.sanlean.totem.data.database

import app.cash.sqldelight.db.SqlDriver
import com.sanlean.totem.Database

object DatabaseSetup {

    fun createDatabase(driver: SqlDriver): Database {
        return Database(driver)
    }
}
