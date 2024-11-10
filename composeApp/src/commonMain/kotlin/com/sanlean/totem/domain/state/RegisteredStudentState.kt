package com.sanlean.totem.domain.state

import com.sanlean.totem.domain.model.Student

sealed class RegisteredStudentState {
    data class StudentRegistered(val data: Student) : RegisteredStudentState()
    object FailOnOnRegister : RegisteredStudentState()
}
