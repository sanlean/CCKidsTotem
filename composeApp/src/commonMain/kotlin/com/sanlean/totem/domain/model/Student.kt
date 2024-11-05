package com.sanlean.totem.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: String,
    val name: String,
    val guardianName: String,
    val age: Int,
    val birthDate: LocalDate?
)