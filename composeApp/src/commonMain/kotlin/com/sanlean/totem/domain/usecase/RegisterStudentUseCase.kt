package com.sanlean.totem.domain.usecase

import com.sanlean.totem.data.Either
import com.sanlean.totem.data.api.ApiRepository
import com.sanlean.totem.data.database.DatabaseRepository
import com.sanlean.totem.domain.constants.EMPTY
import com.sanlean.totem.domain.model.Student

class RegisterStudentUseCase(
    private val apiRepository: ApiRepository,
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke(studentName: String, guardianName: String, age: Int): Student? {
        return when (val apiResponse = apiRepository.registerStudent(studentName, age, guardianName)) {
            is Either.Right -> apiResponse.value.let { isStudentRegistered ->
                if (isStudentRegistered) {
                    Student(EMPTY, studentName, guardianName, age)
                } else {
                    registerStudentInLocalDatabase(studentName, guardianName, age)
                }
            }

            is Either.Left -> {
                registerStudentInLocalDatabase(studentName, guardianName, age)
            }
        }
    }

    private suspend fun registerStudentInLocalDatabase(studentName: String, guardianName: String, age: Int): Student? {
        return when (databaseRepository.registerStudent(studentName, age, guardianName)) {
            is Either.Right -> Student(EMPTY, studentName, guardianName, age)
            is Either.Left -> null
        }
    }
}
