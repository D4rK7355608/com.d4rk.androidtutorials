package com.d4rk.androidtutorials.ui.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.model.ui.screens.UiHomeScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class LessonsViewModel(application : Application) : BaseViewModel(application = application) {

    val _lessons = MutableStateFlow<List<UiHomeScreen>>(emptyList())
    val lessons : StateFlow<List<UiHomeScreen>> = _lessons.asStateFlow()

    fun initializeVisibilityStates() {
        viewModelScope.launch(coroutineExceptionHandler) {
            delay(50L)
            _visibilityStates.value =
                    List(_lessons.value.firstOrNull()?.lessons?.size ?: 0) { false }
            _lessons.value.firstOrNull()?.lessons?.indices?.forEach { index ->
                delay(index * 8L)
                _visibilityStates.value = List(_visibilityStates.value.size) { lessonIndex ->
                    lessonIndex == index || _visibilityStates.value[lessonIndex]
                }
            }
        }
    }
}