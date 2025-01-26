package com.d4rk.androidtutorials.ui.screens.settings.general

import androidx.compose.runtime.Composable
import com.d4rk.android.libs.apptoolkit.ui.screens.settings.about.AboutSettingsList
import com.d4rk.android.libs.apptoolkit.ui.screens.settings.advanced.AdvancedSettingsList
import com.d4rk.android.libs.apptoolkit.ui.screens.settings.display.DisplaySettingsList
import com.d4rk.android.libs.apptoolkit.ui.screens.settings.display.theme.ThemeSettingsList
import com.d4rk.android.libs.apptoolkit.ui.screens.settings.privacy.PrivacySettingsList
import com.d4rk.android.libs.apptoolkit.ui.screens.settings.privacy.usage.UsageAndDiagnosticsList
import com.d4rk.androidtutorials.ui.components.navigation.TopAppBarScaffoldWithBackButton
import com.d4rk.androidtutorials.utils.providers.AppAboutSettingsProvider
import com.d4rk.androidtutorials.utils.providers.AppAdvancedSettingsProvider
import com.d4rk.androidtutorials.utils.providers.AppDisplaySettingsProvider
import com.d4rk.androidtutorials.utils.providers.AppPrivacySettingsProvider
import com.d4rk.androidtutorials.utils.providers.AppUsageAndDiagnosticsProvider

@Composable
fun GeneralSettingsScreen(
    title : String , content : SettingsContent? , onBackClicked : () -> Unit
) {
    TopAppBarScaffoldWithBackButton(title = title , onBackClicked = onBackClicked) { paddingValues ->
        when (content) {
            SettingsContent.ABOUT -> AboutSettingsList(
                paddingValues = paddingValues , provider = AppAboutSettingsProvider()
            )

            SettingsContent.ADVANCED -> AdvancedSettingsList(
                paddingValues = paddingValues , provider = AppAdvancedSettingsProvider()
            )

            SettingsContent.DISPLAY -> DisplaySettingsList(
                paddingValues = paddingValues , provider = AppDisplaySettingsProvider()
            )

            SettingsContent.PRIVACY -> PrivacySettingsList(
                paddingValues = paddingValues , provider = AppPrivacySettingsProvider()
            )

            SettingsContent.THEME -> ThemeSettingsList(
                paddingValues = paddingValues
            )

            SettingsContent.USAGE_AND_DIAGNOSTICS -> UsageAndDiagnosticsList(
                paddingValues = paddingValues,
                provider = AppUsageAndDiagnosticsProvider()
            )

            else -> {}
        }
    }
}