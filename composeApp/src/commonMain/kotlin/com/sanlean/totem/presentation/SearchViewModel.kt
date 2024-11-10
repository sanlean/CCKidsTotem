package com.sanlean.totem.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanlean.totem.domain.model.Student
import com.sanlean.totem.domain.state.MutableResponseListFlow
import com.sanlean.totem.domain.state.ResponseListFlow
import com.sanlean.totem.domain.state.ResponseState.Loading
import com.sanlean.totem.domain.state.ResponseState.Success
import com.sanlean.totem.domain.usecase.KeyboardTypeUseCase
import com.sanlean.totem.domain.usecase.SearchStudentUseCase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchStudentUseCase,
    private val keyboardTypeUseCase: KeyboardTypeUseCase
) : ViewModel() {
    private val _searchList: MutableResponseListFlow<Student> = MutableResponseListFlow()
    val searchList: ResponseListFlow<Student> = _searchList

    fun searchStudents(search: String) {
        viewModelScope.launch {
            _searchList.value = Loading
            _searchList.value = Success(searchUseCase(search).toList())
        }
    }

    fun shouldUseComposeKeyboard() = keyboardTypeUseCase.shouldUseComposeKeyboard()
}
