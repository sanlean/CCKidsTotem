package com.sanlean.totem.domain.localization

import java.util.Locale

actual object LocalizationWrapper {
    actual fun changeLang(language: Language) {
        val locale = Locale.Builder().setLanguage(language.language).setRegion(language.region).build()
        Locale.setDefault(locale)
    }
}