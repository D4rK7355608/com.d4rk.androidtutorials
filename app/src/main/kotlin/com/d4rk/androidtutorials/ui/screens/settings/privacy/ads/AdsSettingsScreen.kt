package com.d4rk.androidtutorials.ui.screens.settings.privacy.ads

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.d4rk.android.libs.apptoolkit.ui.components.modifiers.bounceClick
import com.d4rk.android.libs.apptoolkit.ui.components.preferences.PreferenceItem
import com.d4rk.android.libs.apptoolkit.ui.components.preferences.SwitchCardComposable
import com.d4rk.android.libs.apptoolkit.utils.helpers.IntentsHelper
import com.d4rk.androidtutorials.BuildConfig
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.data.core.AppCoreManager
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.ui.components.navigation.TopAppBarScaffoldWithBackButton
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AdsSettingsScreen(activity : AdsSettingsActivity) {
    val context : Context = LocalContext.current
    val dataStore : DataStore = AppCoreManager.dataStore
    val switchState : State<Boolean> = dataStore.ads.collectAsState(initial = ! BuildConfig.DEBUG)
    val coroutineScope : CoroutineScope = rememberCoroutineScope()

    TopAppBarScaffoldWithBackButton(title = stringResource(id = com.d4rk.android.libs.apptoolkit.R.string.ads) ,
                                    onBackClicked = { activity.finish() }) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues) ,
            ) {
                item(key = "display_ads") {
                    SwitchCardComposable(
                        title = stringResource(id = com.d4rk.android.libs.apptoolkit.R.string.display_ads) ,
                        switchState = switchState
                    ) { isChecked ->
                        coroutineScope.launch {
                            dataStore.saveAds(isChecked = isChecked)
                        }
                    }
                }
                item {
                    Box(modifier = Modifier.padding(horizontal = 8.dp)) {
                        PreferenceItem(title = stringResource(id = com.d4rk.android.libs.apptoolkit.R.string.personalized_ads) ,
                                       enabled = switchState.value ,
                                       summary = stringResource(id = com.d4rk.android.libs.apptoolkit.R.string.summary_ads_personalized_ads) ,
                                       onClick = {
                                           val params : ConsentRequestParameters =
                                                   ConsentRequestParameters.Builder()
                                                           .setTagForUnderAgeOfConsent(false)
                                                           .build()
                                           val consentInformation : ConsentInformation =
                                                   UserMessagingPlatform.getConsentInformation(
                                                       context
                                                   )
                                           consentInformation.requestConsentInfoUpdate(activity ,
                                                                                       params ,
                                                                                       {
                                                                                           activity.openForm()
                                                                                       } ,
                                                                                       {})
                                       })
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 24.dp)
                    ) {
                        Icon(imageVector = Icons.Outlined.Info , contentDescription = null)
                        Spacer(modifier = Modifier.height(height = 24.dp))
                        Text(text = stringResource(id = com.d4rk.android.libs.apptoolkit.R.string.summary_ads))

                        val annotatedString : AnnotatedString = buildAnnotatedString {
                            val startIndex : Int = length
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.primary ,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append(stringResource(id = com.d4rk.android.libs.apptoolkit.R.string.learn_more))
                            }
                            val endIndex : Int = length

                            addStringAnnotation(
                                tag = "URL" ,
                                annotation = "https://sites.google.com/view/d4rk7355608/more/apps/ads-help-center" ,
                                start = startIndex ,
                                end = endIndex
                            )
                        }

                        Text(text = annotatedString , modifier = Modifier
                                .bounceClick()
                                .clickable {
                                    annotatedString
                                            .getStringAnnotations(
                                                tag = "URL" ,
                                                start = 0 ,
                                                end = annotatedString.length
                                            )
                                            .firstOrNull()
                                            ?.let { annotation ->
                                                IntentsHelper.openUrl(
                                                    context = context , url = annotation.item
                                                )
                                            }
                                })
                    }
                }
            }
        }
    }
}