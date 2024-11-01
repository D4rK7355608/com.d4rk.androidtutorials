package com.d4rk.androidtutorials.ui.screens.settings.display.theme.style

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryLight = Color(color = 0xFF4F4EC8)
val onPrimaryLight = Color(color = 0xFFFFFFFF)
val primaryContainerLight = Color(color = 0xFF9292FF)
val onPrimaryContainerLight = Color(color = 0xFF050049)
val secondaryLight = Color(color = 0xFF5A5A89)
val onSecondaryLight = Color(color = 0xFFFFFFFF)
val secondaryContainerLight = Color(color = 0xFFCFCEFF)
val onSecondaryContainerLight = Color(color = 0xFF393966)
val tertiaryLight = Color(color = 0xFF99358E)
val onTertiaryLight = Color(color = 0xFFFFFFFF)
val tertiaryContainerLight = Color(color = 0xFFE275D1)
val onTertiaryContainerLight = Color(color = 0xFF230020)
val errorLight = Color(color = 0xFFBA1A1A)
val onErrorLight = Color(color = 0xFFFFFFFF)
val errorContainerLight = Color(color = 0xFFFFDAD6)
val onErrorContainerLight = Color(color = 0xFF410002)
val backgroundLight = Color(color = 0xFFFCF8FF)
val onBackgroundLight = Color(color = 0xFF1B1B22)
val surfaceLight = Color(color = 0xFFFCF8FF)
val onSurfaceLight = Color(color = 0xFF1B1B22)
val surfaceVariantLight = Color(color = 0xFFE3E0F2)
val onSurfaceVariantLight = Color(color = 0xFF464553)
val outlineLight = Color(color = 0xFF777585)
val outlineVariantLight = Color(color = 0xFFC7C4D6)
val scrimLight = Color(color = 0xFF000000)
val inverseSurfaceLight = Color(color = 0xFF303038)
val inverseOnSurfaceLight = Color(color = 0xFFF3EFFA)
val inversePrimaryLight = Color(color = 0xFFC2C1FF)
val surfaceDimLight = Color(color = 0xFFDCD8E3)
val surfaceBrightLight = Color(color = 0xFFFCF8FF)
val surfaceContainerLowestLight = Color(color = 0xFFFFFFFF)
val surfaceContainerLowLight = Color(color = 0xFFF5F2FD)
val surfaceContainerLight = Color(color = 0xFFF0ECF7)
val surfaceContainerHighLight = Color(color = 0xFFEAE6F1)
val surfaceContainerHighestLight = Color(color = 0xFFE4E1EC)

val primaryDark = Color(color = 0xFFC2C1FF)
val onPrimaryDark = Color(color = 0xFF1C129A)
val primaryContainerDark = Color(color = 0xFF6665E0)
val onPrimaryContainerDark = Color(color = 0xFFFFFFFF)
val secondaryDark = Color(color = 0xFFC3C2F8)
val onSecondaryDark = Color(color = 0xFF2B2C58)
val secondaryContainerDark = Color(color = 0xFF393966)
val onSecondaryContainerDark = Color(color = 0xFFCECDFF)
val tertiaryDark = Color(color = 0xFFFFABED)
val onTertiaryDark = Color(color = 0xFF5C0057)
val tertiaryContainerDark = Color(color = 0xFFB44CA6)
val onTertiaryContainerDark = Color(color = 0xFFFFFFFF)
val errorDark = Color(color = 0xFFFFB4AB)
val onErrorDark = Color(color = 0xFF690005)
val errorContainerDark = Color(color = 0xFF93000A)
val onErrorContainerDark = Color(color = 0xFFFFDAD6)
val backgroundDark = Color(color = 0xFF13131A)
val onBackgroundDark = Color(color = 0xFFE4E1EC)
val surfaceDark = Color(color = 0xFF13131A)
val onSurfaceDark = Color(color = 0xFFE4E1EC)
val surfaceVariantDark = Color(color = 0xFF464553)
val onSurfaceVariantDark = Color(color = 0xFFC7C4D6)
val outlineDark = Color(color = 0xFF918F9F)
val outlineVariantDark = Color(color = 0xFF464553)
val scrimDark = Color(color = 0xFF000000)
val inverseSurfaceDark = Color(color = 0xFFE4E1EC)
val inverseOnSurfaceDark = Color(color = 0xFF303038)
val inversePrimaryDark = Color(color = 0xFF4F4EC8)
val surfaceDimDark = Color(color = 0xFF13131A)
val surfaceBrightDark = Color(color = 0xFF393841)
val surfaceContainerLowestDark = Color(color = 0xFF0E0D15)
val surfaceContainerLowDark = Color(color = 0xFF1B1B22)
val surfaceContainerDark = Color(color = 0xFF1F1F26)
val surfaceContainerHighDark = Color(color = 0xFF2A2931)
val surfaceContainerHighestDark = Color(color = 0xFF35343C)

object Colors {
    @Composable
    fun primaryText() = MaterialTheme.colorScheme.onBackground

    @Composable
    fun secondaryText() = MaterialTheme.colorScheme.onSurface

    @Composable
    fun highlightedText() = MaterialTheme.colorScheme.primary
}