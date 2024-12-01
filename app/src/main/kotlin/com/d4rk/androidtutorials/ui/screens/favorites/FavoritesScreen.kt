package com.d4rk.androidtutorials.ui.screens.favorites

import android.content.Context
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d4rk.androidtutorials.data.model.ui.error.UiErrorModel
import com.d4rk.androidtutorials.data.model.ui.screens.UiLesson
import com.d4rk.androidtutorials.ui.components.dialogs.ErrorAlertDialog
import com.d4rk.androidtutorials.ui.components.layouts.LessonListLayout
import com.d4rk.androidtutorials.ui.screens.loading.LoadingScreen

@Composable
fun FavoritesScreen() {
    val viewModel : FavoritesViewModel = viewModel()
    val uiErrorModel : UiErrorModel by viewModel.uiErrorModel.collectAsState()
    val isLoading : Boolean by viewModel.isLoading.collectAsState()
    val favoriteLessons : List<UiLesson> by viewModel.lessons.collectAsState()
    val transition : Transition<Boolean> =
            updateTransition(targetState = ! isLoading , label = "LoadingTransition")
    val progressAlpha : Float by transition.animateFloat(label = "Progress Alpha") {
        if (it) 0f else 1f
    }

    val visibilityStates by viewModel.visibilityStates.collectAsState()

    val context : Context = LocalContext.current

    if (uiErrorModel.showErrorDialog) {
        ErrorAlertDialog(errorMessage = uiErrorModel.errorMessage ,
                         onDismiss = { viewModel.dismissErrorDialog() })
    }

    if (isLoading) {
        LoadingScreen(progressAlpha)
    }
    else {
        if (favoriteLessons.isEmpty()) {
            NoLessonsFound(text = "No favorite lessons found.")
        }
        else {
            LessonListLayout(
                lessons = favoriteLessons,
                context = context,
                visibilityStates = visibilityStates
            )
        }
    }
}

@Composable
fun NoLessonsFound(text : String) {
    Box(
        modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
    ) {
        Text(text = text)
    }
}