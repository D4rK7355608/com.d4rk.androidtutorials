package com.d4rk.androidtutorials.ui.screens.help

import android.content.Context
import android.view.SoundEffectConstants
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.ui.components.AnimatedExtendedFloatingActionButton
import com.d4rk.androidtutorials.ui.components.animations.bounceClick
import com.d4rk.androidtutorials.ui.components.dialogs.VersionInfoAlertDialog
import com.d4rk.androidtutorials.utils.IntentUtils
import com.d4rk.androidtutorials.utils.rememberHtmlData
import com.google.android.play.core.review.ReviewInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(activity : HelpActivity , viewModel : HelpViewModel) {
    val scrollBehavior : TopAppBarScrollBehavior =
            TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    var showMenu : Boolean by remember { mutableStateOf(value = false) }
    val context : Context = LocalContext.current
    val view : View = LocalView.current
    val isFabVisible by viewModel.isFabVisible.collectAsState()
    val showDialog : MutableState<Boolean> = remember { mutableStateOf(value = false) }
    val reviewInfo : ReviewInfo? = viewModel.reviewInfo.value

    val htmlData = rememberHtmlData()
    val changelogHtmlString = htmlData.value.first
    val eulaHtmlString = htmlData.value.second

    if (reviewInfo != null) {
        LaunchedEffect(key1 = reviewInfo) {
            viewModel.requestReviewFlow()
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection) ,
        topBar = {
            LargeTopAppBar(
                title = { Text(text = stringResource(id = R.string.help)) } ,
                navigationIcon = {
                    IconButton(modifier = Modifier.bounceClick() , onClick = {
                        view.playSoundEffect(SoundEffectConstants.CLICK)
                        activity.finish()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack , contentDescription = null
                        )
                    }
                } ,


                actions = {
                    IconButton(modifier = Modifier.bounceClick() , onClick = {
                        view.playSoundEffect(SoundEffectConstants.CLICK)
                        showMenu = true
                    }) {
                        Icon(
                            Icons.Default.MoreVert , contentDescription = "Localized description"
                        )
                    }
                    DropdownMenu(expanded = showMenu , onDismissRequest = {
                        showMenu = false
                    }) {
                        DropdownMenuItem(modifier = Modifier.bounceClick() ,
                                         text = { Text(text = stringResource(id = R.string.view_in_google_play_store)) } ,
                                         onClick = {
                                             view.playSoundEffect(SoundEffectConstants.CLICK)
                                             IntentUtils.openUrl(
                                                 context ,
                                                 url = "https://play.google.com/store/apps/details?id=${activity.packageName}"
                                             )
                                         })
                        DropdownMenuItem(modifier = Modifier.bounceClick() ,
                                         text = { Text(text = stringResource(id = R.string.version_info)) } ,
                                         onClick = {
                                             view.playSoundEffect(SoundEffectConstants.CLICK)
                                             showDialog.value = true
                                         })
                        DropdownMenuItem(modifier = Modifier.bounceClick() ,
                                         text = { Text(text = stringResource(id = R.string.beta_program)) } ,
                                         onClick = {
                                             view.playSoundEffect(SoundEffectConstants.CLICK)
                                             IntentUtils.openUrl(
                                                 context ,
                                                 url = "https://play.google.com/apps/testing/${activity.packageName}"
                                             )
                                         })
                        DropdownMenuItem(modifier = Modifier.bounceClick() ,
                                         text = { Text(text = stringResource(id = R.string.terms_of_service)) } ,
                                         onClick = {
                                             view.playSoundEffect(SoundEffectConstants.CLICK)
                                             IntentUtils.openUrl(
                                                 context ,
                                                 url = "https://sites.google.com/view/d4rk7355608/more/apps/terms-of-service"
                                             )
                                         })
                        DropdownMenuItem(modifier = Modifier.bounceClick() ,
                                         text = { Text(text = stringResource(id = R.string.privacy_policy)) } ,
                                         onClick = {
                                             view.playSoundEffect(SoundEffectConstants.CLICK)
                                             IntentUtils.openUrl(
                                                 context ,
                                                 url = "https://sites.google.com/view/d4rk7355608/more/apps/privacy-policy"
                                             )
                                         })
                        DropdownMenuItem(modifier = Modifier.bounceClick() ,
                                         text = { Text(text = stringResource(id = R.string.oss_license_title)) } ,
                                         onClick = {
                                             view.playSoundEffect(SoundEffectConstants.CLICK)
                                             IntentUtils.openLicensesScreen(
                                                 context = context ,
                                                 eulaHtmlString = eulaHtmlString ,
                                                 changelogHtmlString = changelogHtmlString
                                             )
                                         })
                    }
                    if (showDialog.value) {
                        VersionInfoAlertDialog(onDismiss = { showDialog.value = false })
                    }
                } ,

                scrollBehavior = scrollBehavior ,


                )
        } ,
        floatingActionButton = {
            AnimatedExtendedFloatingActionButton(visible = isFabVisible , onClick = {
                view.playSoundEffect(SoundEffectConstants.CLICK)
                viewModel.reviewInfo.value?.let { safeReviewInfo ->
                    viewModel.launchReviewFlow(
                        activity , safeReviewInfo
                    )
                }
            } , text = { Text(text = stringResource(id = R.string.feedback)) } , icon = {
                Icon(
                    Icons.Default.MailOutline , contentDescription = null
                )
            })
        } ,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                    .padding(start = 16.dp , end = 16.dp)
                    .fillMaxSize()
                    .safeDrawingPadding()
        ) {
            Column(modifier = Modifier.padding(paddingValues)) {
                Text(
                    text = stringResource(id = R.string.faq) ,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                Card(modifier = Modifier.fillMaxWidth()) {
                    FAQComposable()
                }
            }
        }
    }
}

@Composable
fun FAQComposable() {
    LazyColumn {
        item {
            QuestionComposable(
                title = stringResource(id = R.string.question_1) ,
                summary = stringResource(id = R.string.summary_preference_faq_1)
            )
        }
        item {
            QuestionComposable(
                title = stringResource(id = R.string.question_2) ,
                summary = stringResource(id = R.string.summary_preference_faq_2)
            )
        }
        item {
            QuestionComposable(
                title = stringResource(id = R.string.question_3) ,
                summary = stringResource(id = R.string.summary_preference_faq_3)
            )
        }
        item {
            QuestionComposable(
                title = stringResource(id = R.string.question_4) ,
                summary = stringResource(id = R.string.summary_preference_faq_4)
            )
        }
        item {
            QuestionComposable(
                title = stringResource(id = R.string.question_5) ,
                summary = stringResource(id = R.string.summary_preference_faq_5)
            )
        }
        item {
            QuestionComposable(
                title = stringResource(id = R.string.question_6) ,
                summary = stringResource(id = R.string.summary_preference_faq_6)
            )
        }
        item {
            QuestionComposable(
                title = stringResource(id = R.string.question_7) ,
                summary = stringResource(id = R.string.summary_preference_faq_7)
            )
        }
        item {
            QuestionComposable(
                title = stringResource(id = R.string.question_8) ,
                summary = stringResource(id = R.string.summary_preference_faq_8)
            )
        }
        item {
            QuestionComposable(
                title = stringResource(id = R.string.question_9) ,
                summary = stringResource(id = R.string.summary_preference_faq_9)
            )
        }
    }
}

@Composable
fun QuestionComposable(title : String , summary : String) {
    Row(
        modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Outlined.QuestionAnswer ,
            contentDescription = null ,
            modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer , shape = CircleShape
                    )
                    .padding(8.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title , style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = summary)
        }
    }
}