package com.d4rk.androidtutorials.ui.screens.home

import android.content.Context
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d4rk.androidtutorials.data.model.ui.error.UiErrorModel
import com.d4rk.androidtutorials.data.model.ui.screens.UiLesson
import com.d4rk.androidtutorials.ui.components.dialogs.ErrorAlertDialog
import com.d4rk.androidtutorials.ui.components.layouts.LessonListLayout
import com.d4rk.androidtutorials.ui.screens.loading.LoadingScreen

@Composable
fun HomeScreen() {
    val viewModel : HomeViewModel = viewModel()
    val uiErrorModel : UiErrorModel by viewModel.uiErrorModel.collectAsState()
    val lessons : List<UiLesson> by viewModel.lessons.collectAsState()
    val isLoading : Boolean by viewModel.isLoading.collectAsState()

    val context : Context = LocalContext.current

    val transition : Transition<Boolean> =
            updateTransition(targetState = ! isLoading , label = "LoadingTransition")
    val progressAlpha : Float by transition.animateFloat(label = "Progress Alpha") {
        if (it) 0f else 1f
    }

    if (uiErrorModel.showErrorDialog) {
        ErrorAlertDialog(errorMessage = uiErrorModel.errorMessage) {
            viewModel.dismissErrorDialog()
        }
    }

    val visibilityStates by viewModel.visibilityStates.collectAsState()

    if (isLoading) {
        LoadingScreen(progressAlpha)
    }
    else {
        LessonListLayout(
            lessons = lessons,
            context = context,
            visibilityStates = visibilityStates
        )
    }
}