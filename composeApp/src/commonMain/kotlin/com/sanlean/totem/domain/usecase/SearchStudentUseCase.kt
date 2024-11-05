package com.sanlean.totem.domain.usecase

import com.sanlean.totem.data.api.ApiRepository

class SearchStudentUseCase(
    private val repository: ApiRepository
) {

    suspend operator fun invoke(studentName: String) = repository.searchStudent(studentName)
}