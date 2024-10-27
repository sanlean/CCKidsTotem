package com.sanlean.totem.data

import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ClassRepository(
    private val dispatcher: CoroutineDispatcher,
    private val client: HttpClient
) {

    suspend fun searchStudent(search: String) = withContext(dispatcher) {
        delay(1000L)
        if (search.length > 2) {
            listOf(
                "Ana", "Beatriz", "Carlos", "Daniel", "Eduardo", "Fernanda", "Gabriel", "Helena",
                "Igor", "Joana", "Karla", "Luiz", "Marcelo", "Nina", "Olavo", "Paulo", "Quintino",
                "Renata", "Sara", "Tatiana", "Ubiratan", "Valéria", "Wagner", "Xavier", "Yasmin",
                "Zora", "Rafael", "Alice", "Davi", "Clara", "Miguel"
            ).filter {
                it.contains(search, ignoreCase = true)
            }
        } else {
            emptyList()
        }
    }
}