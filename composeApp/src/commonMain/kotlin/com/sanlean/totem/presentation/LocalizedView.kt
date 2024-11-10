package com.sanlean.totem.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.sanlean.totem.domain.localization.Language

val LocalLocalization = staticCompositionLocalOf { Language.getDefault().isoFormat() }

@Composable
fun LocalizedApp(language: String = Language.getDefault().isoFormat(), content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalLocalization provides language,
        content = content
    )
}
