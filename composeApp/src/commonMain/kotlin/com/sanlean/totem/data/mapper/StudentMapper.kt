package com.sanlean.totem.data.mapper

import com.sanlean.totem.StudentEntity
import com.sanlean.totem.domain.model.Student
import kotlinx.datetime.LocalDate
import java.time.format.DateTimeParseException

fun StudentEntity.mapToBusiness() = Student(
    id = id,
    name = name,
    guardianName = guardian_name,
    age = age?.toInt() ?: 0,
    birthDate = birth_date?.let {
        try {
            LocalDate.parse(it, LocalDate.Formats.ISO_BASIC)
        } catch (e: DateTimeParseException) {
            null
        }
    }
)
