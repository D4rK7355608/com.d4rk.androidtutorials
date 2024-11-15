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

    private val _visibilityStates = mutableListOf<MutableStateFlow<Boolean>>()

    fun initializeVisibilityStates() {
        if (_visibilityStates.size < _lessons.value.size) {
            _visibilityStates.addAll(List(size = _lessons.value.size - _visibilityStates.size) { MutableStateFlow(
                value = false
            ) })
        }

        viewModelScope.launch {
            _visibilityStates.forEachIndexed { index, visibilityState ->
                delay(timeMillis = index * 50L)
                visibilityState.value = true
            }
        }
    }


    fun getVisibilityState(index : Int) : StateFlow<Boolean> {
        val lessonsSize = _lessons.value.size

        if (index in 0 until lessonsSize && index >= _visibilityStates.size) {
            synchronized(_visibilityStates) {
                repeat(times = lessonsSize - _visibilityStates.size) {
                    _visibilityStates.add(MutableStateFlow(false))
                }
            }

            viewModelScope.launch {
                _visibilityStates.asReversed().withIndex().forEach { (i , state) ->
                    if (! state.value && i >= index) {
                        delay(timeMillis = i * 50L)
                        state.value = true
                    }
                }
            }
        }

        return _visibilityStates.getOrElse(index) { MutableStateFlow(false) }
    }
}