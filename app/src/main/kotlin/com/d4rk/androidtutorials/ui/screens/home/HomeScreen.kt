package com.d4rk.androidtutorials.ui.screens.home

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d4rk.androidtutorials.data.model.ui.error.UiErrorModel
import com.d4rk.androidtutorials.data.model.ui.screens.UiLesson
import com.d4rk.androidtutorials.ui.components.dialogs.ErrorAlertDialog
import com.d4rk.androidtutorials.ui.components.lessons.LessonItem
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

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _ , event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.initializeVisibilityStates()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        return@DisposableEffect onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    if (isLoading) {
        LoadingScreen(progressAlpha)
    }
    else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp) ,
            verticalArrangement = Arrangement.spacedBy(16.dp) ,
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(lessons) { index , lesson ->
                val isVisible by viewModel.getVisibilityState(index).collectAsState()

                AnimatedVisibility(
                    visible = isVisible ,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 300 , delayMillis = index * 50
                        )
                    ) + slideInVertically(
                        initialOffsetY = { 50 } ,
                        animationSpec = tween(durationMillis = 300 , delayMillis = index * 50)
                    ) ,
                    exit = fadeOut() + slideOutVertically(targetOffsetY = { 50 }) ,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    LessonItem(
                        lesson = lesson , context = context
                    )
                }
            }
        }
    }
}