package com.sanlean.totem.data.api

import com.sanlean.totem.data.Either
import com.sanlean.totem.data.left
import com.sanlean.totem.data.right
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <R> makeRequest(request: suspend () -> R): Either<Exception, R> {
    return try {
        request.invoke().right()
    } catch (e: Exception) {
        e.left()
    }
}

suspend fun <R> makeRequest(
    dispatcher: CoroutineDispatcher,
    request: suspend () -> R
): Either<Exception, R> = withContext(dispatcher) {
    makeRequest(request)
}