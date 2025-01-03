package com.sanlean.totem

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sanlean.totem.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Totem",
        state = androidx.compose.ui.window.WindowState(
            height = 1920.dp,
            width = 1080.dp,
            // placement = androidx.compose.ui.window.WindowPlacement.Fullscreen
        )
    ) {
        initKoin()
        App()
    }
}
