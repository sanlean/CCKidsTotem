package com.sanlean.totem.data.database

import com.sanlean.totem.Database

object DatabaseSetup {

    fun createDatabase(driverFactory: DriverFactory): Database {
        val driver = driverFactory.createDriver()
        val database = Database(driver)

        return database
    }
}