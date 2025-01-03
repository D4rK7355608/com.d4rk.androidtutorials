package com.d4rk.androidtutorials.ui.screens.settings.about

import android.content.Context
import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.d4rk.androidtutorials.BuildConfig
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.ui.components.preferences.PreferenceCategoryItem
import com.d4rk.androidtutorials.ui.components.preferences.PreferenceItem
import com.d4rk.androidtutorials.ui.components.snackbar.Snackbar
import com.d4rk.androidtutorials.ui.components.navigation.TopAppBarScaffoldWithBackButton
import com.d4rk.androidtutorials.utils.helpers.ClipboardHelper
import com.d4rk.androidtutorials.utils.helpers.IntentsHelper
import com.d4rk.androidtutorials.utils.rememberHtmlData

@Composable
fun AboutSettingsComposable(activity : AboutSettingsActivity) {
    val context : Context = LocalContext.current

    var showSnackbar : Boolean by remember { mutableStateOf(value = false) }

    val htmlData = rememberHtmlData()
    val changelogHtmlString = htmlData.value.first
    val eulaHtmlString = htmlData.value.second

    TopAppBarScaffoldWithBackButton(title = stringResource(id = R.string.about) ,
                                    onBackClicked = { activity.finish() }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
            LazyColumn(
                modifier = Modifier.fillMaxHeight()
            ) {
                item {
                    PreferenceCategoryItem(title = stringResource(id = R.string.app_info))
                }
                item {
                    PreferenceItem(
                        title = stringResource(id = R.string.app_name) ,
                        summary = stringResource(id = R.string.copyright) ,
                    )
                }
                item {
                    PreferenceItem(
                        title = stringResource(id = R.string.app_build_version) ,
                        summary = "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
                    )
                }
                item {
                    PreferenceItem(title = stringResource(R.string.oss_license_title) ,
                                   summary = stringResource(id = R.string.summary_preference_settings_oss) ,
                                   onClick = {
                                       IntentsHelper.openLicensesScreen(
                                           context = context ,
                                           eulaHtmlString = eulaHtmlString ,
                                           changelogHtmlString = changelogHtmlString
                                       )
                                   })
                }
                item {
                    PreferenceCategoryItem(title = stringResource(id = R.string.device_info))
                }
                item {
                    val version : String = stringResource(
                        id = R.string.app_build ,
                        "${stringResource(id = R.string.manufacturer)} ${Build.MANUFACTURER}" ,
                        "${stringResource(id = R.string.device_model)} ${Build.MODEL}" ,
                        "${stringResource(id = R.string.android_version)} ${Build.VERSION.RELEASE}" ,
                        "${stringResource(id = R.string.api_level)} ${Build.VERSION.SDK_INT}" ,
                        "${stringResource(id = R.string.arch)} ${Build.SUPPORTED_ABIS.joinToString()}" ,
                        if (BuildConfig.DEBUG) stringResource(id = R.string.debug)
                        else stringResource(id = R.string.release)
                    )

                    PreferenceItem(title = stringResource(id = R.string.device_info) ,
                                   summary = version ,
                                   onClick = {
                                       ClipboardHelper.copyTextToClipboard(context = context ,
                                                                           label = "Device Info" ,
                                                                           text = version ,
                                                                           onShowSnackbar = {
                                                                               showSnackbar = true
                                                                           })
                                   })
                }
            }

            Snackbar(message = stringResource(id = R.string.snack_device_info_copied) ,
                     showSnackbar = showSnackbar ,
                     onDismiss = { showSnackbar = false })
        }
    }
}