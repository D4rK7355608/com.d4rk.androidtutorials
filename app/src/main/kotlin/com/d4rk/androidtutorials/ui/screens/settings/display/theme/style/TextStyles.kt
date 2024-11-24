package com.d4rk.androidtutorials.ui.screens.settings.display.theme.style

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

object TextStyles {
    @Composable
    fun header() = MaterialTheme.typography.headlineMedium

    @Composable
    fun body() = MaterialTheme.typography.bodyMedium

    @Composable
    fun label() = MaterialTheme.typography.labelMedium
}
