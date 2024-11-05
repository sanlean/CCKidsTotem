package com.sanlean.totem.data

import com.sanlean.totem.domain.model.Student

interface Repository {
    suspend fun searchStudent(
        search: String
    ): List<Student>

    suspend fun registerStudent(
        name: String,
        age: Int,
        guardianName: String
    ): Boolean
}