package com.one.airsync

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.ui.tooling.preview.Preview

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "AirSync",
    ) {
        App()
    }
}

@Preview
@Composable
fun WindowsPreview() {
    App()
}