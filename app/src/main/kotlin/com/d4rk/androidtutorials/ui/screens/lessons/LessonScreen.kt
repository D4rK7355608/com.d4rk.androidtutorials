package com.d4rk.androidtutorials.ui.screens.lessons

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.error.UiErrorModel
import com.d4rk.androidtutorials.data.model.ui.screens.UiLessonScreenModel
import com.d4rk.androidtutorials.ui.components.dialogs.ErrorAlertDialog
import com.d4rk.androidtutorials.ui.components.layouts.LessonContentLayout
import com.d4rk.androidtutorials.ui.components.navigation.TopAppBarScaffoldWithBackButton
import com.d4rk.androidtutorials.ui.screens.loading.LoadingScreen

@Composable
fun LessonScreen(
    lesson : UiLessonScreenModel ,
    activity : LessonActivity ,
    viewModel : LessonViewModel ,
) {
    val dataStore : DataStore = DataStore.getInstance(context = activity)
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
        if (isLoading) {
            LoadingScreen(progressAlpha)
        }
        else {
            LessonContentLayout(
                dataStore = dataStore ,
                lesson = lesson ,
                paddingValues = paddingValues ,
                scrollState = scrollState ,
            )
        }
    }
}