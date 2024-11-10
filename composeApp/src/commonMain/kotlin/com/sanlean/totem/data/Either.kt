package com.sanlean.totem.data

sealed class Either<out L : Exception, out R> {
    data class Left<out L : Exception, out R>(val error: L) : Either<L, R>()
    data class Right<out L : Exception, out R>(val value: R) : Either<L, R>()
}

fun <L : Exception> L.left() = Either.Left<L, Nothing>(this)

fun <R> R.right() = Either.Right<Nothing, R>(this)
