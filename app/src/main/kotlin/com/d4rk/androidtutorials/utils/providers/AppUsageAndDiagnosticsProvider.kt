package com.d4rk.androidtutorials.utils.providers

import com.d4rk.android.libs.apptoolkit.utils.interfaces.providers.UsageAndDiagnosticsSettingsProvider
import com.d4rk.androidtutorials.BuildConfig

class AppUsageAndDiagnosticsProvider : UsageAndDiagnosticsSettingsProvider {

    override val isDebugBuild : Boolean
        get() {
            return BuildConfig.DEBUG
        }
}