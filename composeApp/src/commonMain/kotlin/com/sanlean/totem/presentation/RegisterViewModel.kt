package com.sanlean.totem.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanlean.totem.domain.model.Student
import com.sanlean.totem.domain.usecase.KeyboardTypeUseCase
import com.sanlean.totem.domain.usecase.SearchStudentUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SearchViewModel(
    private val searchUseCase: SearchStudentUseCase,
    private val keyboardTypeUseCase: KeyboardTypeUseCase
) : ViewModel() {
    private val _searchList = MutableStateFlow<List<Student>>(emptyList())
    val searchList: StateFlow<List<Student>> = _searchList

    fun searchStudents(search: String) {
        System.out.println("search for $search")
        viewModelScope.launch {
            _searchList.value = searchUseCase(search).toList()
        }
    }

    fun shouldUseComposeKeyboard() = keyboardTypeUseCase.shouldUseComposeKeyboard()
}