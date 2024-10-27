package com.sanlean.totem.domain

import com.sanlean.totem.data.ClassRepository

class SearchStudentUseCase(
    private val repository: ClassRepository
) {

    suspend operator fun invoke(studentName: String) = repository.searchStudent(studentName)
}