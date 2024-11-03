package com.sanlean.totem.domain.localization

sealed class Language(val language: String, val region: String) {
    data object English: Language("en", "US")
    data object Spanish: Language("es", "BO")
    data object Portuguese: Language("pt", "BR")

    fun isoFormat() = "$language-$region"
    companion object{
        fun getDefault() = Portuguese
    }
}