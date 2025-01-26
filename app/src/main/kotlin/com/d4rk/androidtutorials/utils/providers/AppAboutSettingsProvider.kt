package com.d4rk.androidtutorials.utils.providers

import android.content.Context
import com.d4rk.androidtutorials.R
import android.os.Build
import com.d4rk.android.libs.apptoolkit.utils.interfaces.providers.AboutSettingsProvider
import com.d4rk.androidtutorials.BuildConfig
import com.d4rk.androidtutorials.data.core.AppCoreManager

class AppAboutSettingsProvider : AboutSettingsProvider {

    private val context : Context = AppCoreManager.instance

    override val appName : String
        get() = context.getString(R.string.app_name)

    override val packageName : String
        get() = context.packageName


    override val appVersion : String
        get() = BuildConfig.VERSION_NAME

    override val appVersionCode : Int
        get() {
            return BuildConfig.VERSION_CODE
        }

    override val copyrightText : String
        get() = context.getString(R.string.copyright)

    override val deviceInfo : String
        get() {
            return context.getString(
                R.string.app_build ,
                "${context.getString(R.string.manufacturer)} ${Build.MANUFACTURER}" ,
                "${context.getString(R.string.device_model)} ${Build.MODEL}" ,
                "${context.getString(R.string.android_version)} ${Build.VERSION.RELEASE}" ,
                "${context.getString(R.string.api_level)} ${Build.VERSION.SDK_INT}" ,
                "${context.getString(R.string.arch)} ${Build.SUPPORTED_ABIS.joinToString()}" ,
                if (BuildConfig.DEBUG) context.getString(R.string.debug) else context.getString(R.string.release)
            )
        }
}