package com.sanlean.totem

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.sanlean.totem.domain.localization.Language
import com.sanlean.totem.domain.localization.LocalizationWrapper
import com.sanlean.totem.presentation.SearchScreen
import com.sanlean.totem.presentation.LocalizedApp
import com.sanlean.totem.presentation.SearchViewModel
import com.sanlean.totem.presentation.WelcomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    var showWelcomeScreen by remember { mutableStateOf(true) }
    var lang by remember { mutableStateOf(Language.getDefault().isoFormat()) }

    MaterialTheme {

        LocalizedApp(language = lang) {
            if (showWelcomeScreen) {
                WelcomeScreen(onStartClicked = { showWelcomeScreen = false }, onLanguageSelected = {language ->
                    LocalizationWrapper.changeLang(language)
                    lang = language.isoFormat()
                })
            } else {
                val searchViewModel = koinViewModel<SearchViewModel>()
                SearchScreen(onBack = { showWelcomeScreen = true }, searchViewModel = searchViewModel)
            }
        }
    }
}