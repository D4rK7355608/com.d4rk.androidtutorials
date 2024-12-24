package com.d4rk.androidtutorials.ui.screens.lessons

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.model.ui.screens.UiLessonScreen
import com.d4rk.androidtutorials.ui.screens.lessons.repository.LessonRepository
import com.d4rk.androidtutorials.ui.viewmodel.LessonsViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LessonViewModel(application : Application) : LessonsViewModel(application) {
    private val repository : LessonRepository = LessonRepository()
    private val _lesson : MutableStateFlow<UiLessonScreen?> = MutableStateFlow(value = null)
    val lesson : StateFlow<UiLessonScreen?> = _lesson.asStateFlow()

    fun getLesson(lessonId : String) {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            showLoading()
            repository.getLessonRepository(lessonId = lessonId) { fetchedLesson ->
                _lesson.value = fetchedLesson
            }
            hideLoading()
        }
    }
}