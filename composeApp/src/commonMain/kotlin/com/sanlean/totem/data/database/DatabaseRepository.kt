package com.sanlean.totem.data.database

import com.sanlean.totem.Database
import com.sanlean.totem.data.Repository
import com.sanlean.totem.data.mapper.mapToBusiness
import com.sanlean.totem.domain.constants.MINIMAL_ASYNC_DELAY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import java.util.*

class DatabaseRepository(
    private val dispatcher: CoroutineDispatcher,
    private val database: Database
) : Repository {

    override suspend fun searchStudent(search: String) = makeQuery(dispatcher) {
        delay(MINIMAL_ASYNC_DELAY)
        database.studentQueries.selectAll().executeAsList().map {
            it.mapToBusiness()
        }
    }

    override suspend fun registerStudent(
        name: String,
        age: Int,
        guardianName: String
    ) = makeQuery(dispatcher) {
        delay(MINIMAL_ASYNC_DELAY)
        database.studentQueries.insertStudent(
            id = UUID.randomUUID().toString(),
            name = name,
            age = age.toLong(),
            birth_date = null,
            guardian_name = guardianName
        )
        true
    }
}
