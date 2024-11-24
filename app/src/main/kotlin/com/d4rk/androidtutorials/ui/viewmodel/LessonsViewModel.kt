package com.d4rk.androidtutorials.ui.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.model.ui.screens.UiLesson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class LessonsViewModel(application : Application) : BaseViewModel(application = application) {

    val _lessons = MutableStateFlow<List<UiLesson>>(emptyList())
    val lessons : StateFlow<List<UiLesson>> = _lessons.asStateFlow()

    fun initializeVisibilityStates() {
        viewModelScope.launch(coroutineExceptionHandler) {
            delay(timeMillis = 50L)
            _visibilityStates.value = List(_lessons.value.size) { false }
            _lessons.value.indices.forEach { index ->
                delay(timeMillis = index * 8L)
                _visibilityStates.value = List(_visibilityStates.value.size) { lessonIndex ->
                    lessonIndex == index || _visibilityStates.value[lessonIndex]
                }
            }
        }
    }
}