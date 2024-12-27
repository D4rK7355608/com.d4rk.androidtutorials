package com.d4rk.androidtutorials.ui.screens.favorites

import android.content.Context
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.HeartBroken
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.data.model.ui.error.UiErrorModel
import com.d4rk.androidtutorials.data.model.ui.screens.UiHomeScreen
import com.d4rk.androidtutorials.ui.components.dialogs.ErrorAlertDialog
import com.d4rk.androidtutorials.ui.components.layouts.LessonListLayout
import com.d4rk.androidtutorials.ui.components.layouts.LoadingScreen
import com.d4rk.androidtutorials.ui.components.layouts.NoLessonsScreen

@Composable
fun FavoritesScreen() {
    val viewModel : FavoritesViewModel = viewModel()
    val uiErrorModel : UiErrorModel by viewModel.uiErrorModel.collectAsState()
    val isLoading : Boolean by viewModel.isLoading.collectAsState()
    val favoriteLessons : List<UiHomeScreen> by viewModel.lessons.collectAsState()

    val transition : Transition<Boolean> =
            updateTransition(targetState = ! isLoading , label = "LoadingTransition")
    val progressAlpha : Float by transition.animateFloat(label = "Progress Alpha") {
        if (it) 0f else 1f
    }

    val visibilityStates : List<Boolean> by viewModel.visibilityStates.collectAsState()

    val context : Context = LocalContext.current

    if (uiErrorModel.showErrorDialog) {
        ErrorAlertDialog(errorMessage = uiErrorModel.errorMessage ,
                         onDismiss = { viewModel.dismissErrorDialog() })
    }

    when {
        isLoading -> {
            LoadingScreen(progressAlpha)
        }

        else -> {
            when (val lessons = favoriteLessons.firstOrNull()?.lessons) {
                null -> NoLessonsScreen(
                    text = R.string.error_loading_favorites,
                    icon = Icons.Outlined.ErrorOutline
                )

                emptyList<UiHomeScreen>() -> NoLessonsScreen(
                    text = R.string.no_favorite_lessons_found,
                    icon = Icons.Outlined.HeartBroken
                )

                else -> LessonListLayout(
                    lessons = lessons,
                    context = context,
                    visibilityStates = visibilityStates
                )
            }
        }
    }
}