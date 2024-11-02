package com.d4rk.androidtutorials.ui.screens.lessons

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.widget.Toast
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CopyAll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import com.d4rk.androidtutorials.constants.ui.lessons.LessonContentTypes
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.error.UiErrorModel
import com.d4rk.androidtutorials.data.model.ui.screens.UiLessonScreenModel
import com.d4rk.androidtutorials.ui.components.ads.AdBanner
import com.d4rk.androidtutorials.ui.components.ads.AdBannerFull
import com.d4rk.androidtutorials.ui.components.ads.LargeBannerAdsComposable
import com.d4rk.androidtutorials.ui.components.animations.bounceClick
import com.d4rk.androidtutorials.ui.components.dialogs.ErrorAlertDialog
import com.d4rk.androidtutorials.ui.components.navigation.TopAppBarScaffoldWithBackButton
import com.d4rk.androidtutorials.ui.screens.loading.LoadingScreen
import com.d4rk.androidtutorials.ui.screens.settings.display.theme.style.Colors
import com.d4rk.androidtutorials.ui.screens.settings.display.theme.style.TextStyles
import com.d4rk.androidtutorials.utils.ClipboardUtil

@Composable
fun LessonScreen(
    lesson : UiLessonScreenModel ,
    activity : LessonActivity ,
    viewModel : LessonViewModel ,
) {
    val uiErrorModel : UiErrorModel by viewModel.uiErrorModel.collectAsState()
    val isLoading : Boolean by viewModel.isLoading.collectAsState()
    val transition : Transition<Boolean> =
            updateTransition(targetState = ! isLoading , label = "LoadingTransition")
    val progressAlpha : Float by transition.animateFloat(label = "Progress Alpha") {
        if (it) 0f else 1f
    }

    if (uiErrorModel.showErrorDialog) {
        ErrorAlertDialog(errorMessage = uiErrorModel.errorMessage ,
                         onDismiss = { viewModel.dismissErrorDialog() })
    }

    TopAppBarScaffoldWithBackButton(
        title = lesson.title ,
        onBackClicked = { activity.finish() }) { paddingValues ->

        if (isLoading) {
            LoadingScreen(progressAlpha)
        }
        else {
            LazyColumn(
                modifier = Modifier
                        .fillMaxHeight()
                        .padding(paddingValues)
            ) {
                items(lesson.content , key = { item -> item.id }) { contentItem ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp)
                    ) {
                        when (contentItem.type) {
                            LessonContentTypes.HEADER -> {
                                StyledText(
                                    text = contentItem.text ,
                                    style = TextStyles.header() ,
                                    color = Colors.primaryText()
                                )
                            }

                            LessonContentTypes.TEXT -> {
                                StyledText(
                                    text = contentItem.text ,
                                    style = TextStyles.body() ,
                                    color = Colors.secondaryText()
                                )
                            }

                            LessonContentTypes.IMAGE -> {
                                StyledImage(
                                    imageUrl = contentItem.src , contentDescription = "Lesson Image"
                                )
                            }

                            LessonContentTypes.CODE -> {
                                CodeBlock(code = contentItem.code , language = contentItem.language)
                            }

                            LessonContentTypes.AD_BANNER -> {
                                AdBanner(dataStore = DataStore(activity))
                            }

                            LessonContentTypes.AD_BANNER_FULL -> {
                                AdBannerFull(dataStore = DataStore(activity))
                            }

                            LessonContentTypes.AD_LARGE_BANNER -> {
                                LargeBannerAdsComposable(dataStore = DataStore(activity))
                            }

                            LessonContentTypes.FULL_IMAGE_BANNER -> {
                                AsyncImage(
                                    model = contentItem.url , contentDescription = "full_image_banner"
                                )
                            }

                            else -> {
                                Text(text = "Unsupported content type: ${contentItem.type}")
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun StyledText(
    text : String ,
    style : TextStyle = TextStyles.body() ,
    color : Color = Colors.primaryText() ,
) {
    val annotatedString = AnnotatedString.fromHtml(text)

    Text(
        text = annotatedString , style = style , color = color
    )
}

@Composable
fun StyledImage(
    imageUrl : String ,
    contentDescription : String? = null ,
    modifier : Modifier = Modifier ,
) {
    val context : Context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context).components {
        if (SDK_INT >= 28) {
            add(AnimatedImageDecoder.Factory())
        }
        else {
            add(GifDecoder.Factory())
        }
    }.build()
    Card(
        modifier = modifier.fillMaxWidth() ,
    ) {
        AsyncImage(
            model = imageUrl ,
            contentDescription = contentDescription ,
            modifier = Modifier.fillMaxWidth() ,
            imageLoader = imageLoader ,
        )
    }
}


@Composable
fun CodeBlock(code: String, language: String?) {
    val context = LocalContext.current
    var showSnackbar by remember { mutableStateOf(value = false) }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = language ?: "unknown",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(end = 8.dp)
                )
                TextButton(
                    modifier = Modifier.bounceClick(),
                    onClick = {
                        ClipboardUtil.copyTextToClipboard(
                            context = context,
                            label = "Code",
                            text = code,
                            onShowSnackbar = {
                                Toast.makeText(context , "Code copied to clipboard" , Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CopyAll,
                        contentDescription = "Copy Code",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                    Text(text = "Copy")
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            SelectionContainer {
                Text(
                    text = code,
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}