package com.sanlean.totem.domain.usecase

import com.sanlean.totem.data.Either
import com.sanlean.totem.data.api.ApiRepository
import com.sanlean.totem.data.database.DatabaseRepository
import com.sanlean.totem.domain.model.Student

class SearchStudentUseCase(
    private val apiRepository: ApiRepository,
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke(studentName: String): List<Student>{
        return when (val apiResponse = apiRepository.searchStudent(studentName)){
            is Either.Right -> apiResponse.value
            is Either.Left -> {
                when (val databaseResponse = databaseRepository.searchStudent(studentName)){
                    is Either.Right -> databaseResponse.value
                    is Either.Left -> emptyList()
                }
            }
        }
    }
}