package com.sanlean.totem.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanlean.totem.domain.state.MutableResponseFlow
import com.sanlean.totem.domain.state.RegisteredStudentState
import com.sanlean.totem.domain.state.RegisteredStudentState.FailOnOnRegister
import com.sanlean.totem.domain.state.RegisteredStudentState.StudentRegistered
import com.sanlean.totem.domain.state.ResponseFlow
import com.sanlean.totem.domain.state.ResponseState.Loading
import com.sanlean.totem.domain.state.ResponseState.Success
import com.sanlean.totem.domain.usecase.KeyboardTypeUseCase
import com.sanlean.totem.domain.usecase.RegisterStudentUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterStudentUseCase,
    private val keyboardTypeUseCase: KeyboardTypeUseCase
) : ViewModel() {
    private val _checkInStatus: MutableResponseFlow<RegisteredStudentState> = MutableResponseFlow()
    val checkInStatus: ResponseFlow<RegisteredStudentState> = _checkInStatus

    fun registerStudents(studentName: String, guardianName: String, age: Int) {
        viewModelScope.launch {
            _checkInStatus.value = Loading
            val registeredStudent = registerUseCase(studentName, guardianName, age)
            if (registeredStudent != null) {
                _checkInStatus.value = Success(StudentRegistered(registeredStudent))
            } else {
                _checkInStatus.value = Success(FailOnOnRegister)
            }
        }
    }

    fun shouldUseComposeKeyboard() = keyboardTypeUseCase.shouldUseComposeKeyboard()
}
