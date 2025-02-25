package com.d4rk.androidtutorials.ui.screens.lessons

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.d4rk.android.libs.apptoolkit.data.model.ui.error.UiErrorModel
import com.d4rk.android.libs.apptoolkit.ui.components.dialogs.ErrorAlertDialog
import com.d4rk.android.libs.apptoolkit.ui.components.layouts.LoadingScreen
import com.d4rk.androidtutorials.data.model.ui.screens.UiLessonScreen
import com.d4rk.androidtutorials.ui.components.layouts.LessonContentLayout
import com.d4rk.androidtutorials.ui.components.navigation.TopAppBarScaffoldWithBackButton

@Composable
fun LessonScreen(
    lesson : UiLessonScreen ,
    activity : LessonActivity ,
    viewModel : LessonViewModel ,
) {
    val uiErrorModel : UiErrorModel by viewModel.uiErrorModel.collectAsState()
    val isLoading : Boolean by viewModel.isLoading.collectAsState()
    val scrollState = rememberScrollState()
    val transition : Transition<Boolean> =
            updateTransition(targetState = ! isLoading , label = "LoadingTransition")
    val progressAlpha : Float by transition.animateFloat(label = "Progress Alpha") {
        if (it) 0f else 1f
    }

    if (uiErrorModel.showErrorDialog) {
        ErrorAlertDialog(errorMessage = uiErrorModel.errorMessage ,
                         onDismiss = { viewModel.dismissErrorDialog() })
    }

    TopAppBarScaffoldWithBackButton(title = lesson.lessonTitle ,
                                    onBackClicked = { activity.finish() }) { paddingValues ->
        when {
            isLoading -> {
                LoadingScreen(progressAlpha = progressAlpha)
            }

            else -> {
                LessonContentLayout(
                    lesson = lesson ,
                    paddingValues = paddingValues ,
                    scrollState = scrollState ,
                )
            }
        }
    }
}