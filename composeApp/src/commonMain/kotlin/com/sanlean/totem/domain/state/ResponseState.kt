package com.sanlean.totem.domain.state

import com.sanlean.totem.domain.state.ResponseState.Idle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class ResponseState<out T> {
    object Idle : ResponseState<Nothing>()
    object Loading : ResponseState<Nothing>()

    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error(val exception: Exception) : ResponseState<Nothing>()
}

typealias MutableResponseFlow<T> = MutableStateFlow<ResponseState<T>>
typealias MutableResponseListFlow<T> = MutableStateFlow<ResponseState<List<T>>>
typealias ResponseFlow<T> = StateFlow<ResponseState<T>>
typealias ResponseListFlow<T> = StateFlow<ResponseState<List<T>>>

fun <T> MutableResponseFlow(state: ResponseState<T> = Idle): MutableResponseFlow<T> = MutableStateFlow(state)
fun <T> MutableResponseListFlow(
    state: ResponseState<List<T>> = Idle
): MutableResponseListFlow<T> = MutableStateFlow(state)
