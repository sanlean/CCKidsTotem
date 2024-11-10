package com.sanlean.totem.data.api

import com.sanlean.totem.data.Repository
import com.sanlean.totem.domain.constants.MINIMAL_ASYNC_DELAY
import com.sanlean.totem.domain.model.Student
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import java.util.*

class ApiRepository(
    private val dispatcher: CoroutineDispatcher,
    private val client: HttpClient
) : Repository {

    override suspend fun searchStudent(search: String) = makeRequest(dispatcher) {
        if (search.length > 2) {
            delay(MINIMAL_ASYNC_DELAY)
            listOf(
                "Ana", "Beatriz", "Carlos", "Daniel", "Eduardo", "Fernanda", "Gabriel", "Helena",
                "Igor", "Joana", "Karla", "Luiz", "Marcelo", "Nina", "Olavo", "Paulo", "Quintino",
                "Renata", "Sara", "Tatiana", "Ubiratan", "Valéria", "Wagner", "Xavier", "Yasmin",
                "Zora", "Rafael", "Alice", "Davi", "Clara", "Miguel"
            ).filter {
                it.contains(search, ignoreCase = true)
            }.map {
                Student(
                    id = UUID.randomUUID().toString(),
                    name = it,
                    guardianName = "Mock Guardian",
                    age = 10
                )
            }
        } else {
            emptyList()
        }
    }

    override suspend fun registerStudent(
        name: String,
        age: Int,
        guardianName: String
    ) = makeRequest(dispatcher) {
        delay(MINIMAL_ASYNC_DELAY)
        true
    }
}
