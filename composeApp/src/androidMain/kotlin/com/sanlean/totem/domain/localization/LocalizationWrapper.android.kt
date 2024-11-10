package com.sanlean.totem.domain.localization

import java.util.Locale

actual object LocalizationWrapper {
    actual fun changeLang(language: Language) {
        val locale = Locale(language.isoFormat())
        Locale.setDefault(locale)
    }
}
