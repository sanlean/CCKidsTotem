package com.sanlean.totem.data

import com.sanlean.totem.domain.model.Student

interface Repository {
    suspend fun searchStudent(
        search: String
    ): Either<Exception, List<Student>>

    suspend fun registerStudent(
        name: String,
        age: Int,
        guardianName: String
    ): Either<Exception, Boolean>
}
