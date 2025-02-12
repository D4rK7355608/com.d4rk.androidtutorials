package com.d4rk.androidtutorials.ui.screens.home

import android.content.Context
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d4rk.android.libs.apptoolkit.data.model.ui.error.UiErrorModel
import com.d4rk.android.libs.apptoolkit.ui.components.dialogs.ErrorAlertDialog
import com.d4rk.android.libs.apptoolkit.ui.components.layouts.LoadingScreen
import com.d4rk.androidtutorials.data.model.ui.screens.UiHomeScreen
import com.d4rk.androidtutorials.ui.components.layouts.LessonListLayout
import com.d4rk.androidtutorials.ui.components.layouts.NoLessonsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel : HomeViewModel = viewModel()
    val uiErrorModel : UiErrorModel by viewModel.uiErrorModel.collectAsState()
    val lessons : List<UiHomeScreen> by viewModel.lessons.collectAsState()
    val isLoading : Boolean by viewModel.isLoading.collectAsState()

    val context : Context = LocalContext.current

    val transition : Transition<Boolean> = updateTransition(targetState = ! isLoading , label = "LoadingTransition")
    val progressAlpha : Float by transition.animateFloat(label = "Progress Alpha") {
        if (it) 0f else 1f
    }

    if (uiErrorModel.showErrorDialog) {
        ErrorAlertDialog(errorMessage = uiErrorModel.errorMessage) {
            viewModel.dismissErrorDialog()
        }
    }

    val visibilityStates : List<Boolean> by viewModel.visibilityStates.collectAsState()

    if (lessons.firstOrNull()?.lessons.isNullOrEmpty() && isLoading) {
        LoadingScreen(progressAlpha = progressAlpha)
    }
    else {
        PullToRefreshBox(isRefreshing = isLoading , onRefresh = { viewModel.getHomeLessons() } , modifier = Modifier.fillMaxSize()) {
            when {
                lessons.firstOrNull()?.lessons?.isNotEmpty() == true -> {
                    LessonListLayout(lessons = lessons.first().lessons , context = context , visibilityStates = visibilityStates)
                }

                else -> {
                    NoLessonsScreen()
                }
            }
        }
    }
}