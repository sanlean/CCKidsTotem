package com.sanlean.totem

fun String.isVowel() = this in "AEIOU"

fun String.applyAccent(accent: String): String {
    return when (accent) {
        "'" -> when (this) {
            "A" -> "Á"
            "E" -> "É"
            "I" -> "Í"
            "O" -> "Ó"
            "U" -> "Ú"
            else -> this
        }
        "~" -> when (this) {
            "A" -> "Ã"
            "O" -> "Õ"
            else -> this
        }
        "^" -> when (this) {
            "A" -> "Â"
            "E" -> "Ê"
            "I" -> "Î"
            "O" -> "Ô"
            "U" -> "Û"
            else -> this
        }
        else -> this
    }
}