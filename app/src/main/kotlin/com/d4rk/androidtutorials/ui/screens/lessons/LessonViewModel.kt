package com.d4rk.androidtutorials.ui.screens.lessons

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiLessonScreenModel
import com.d4rk.androidtutorials.ui.screens.lessons.repository.LessonRepository
import com.d4rk.androidtutorials.ui.viewmodel.LessonsViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LessonViewModel(application : Application) : LessonsViewModel(application) {
    private val repository = LessonRepository(DataStore(application) , application)
    private val _lesson = MutableStateFlow<UiLessonScreenModel?>(value = null)
    val lesson : StateFlow<UiLessonScreenModel?> = _lesson.asStateFlow()

    fun getLesson(lessonId : String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            showLoading()
            repository.getLessonRepository(lessonId) { fetchedLesson ->
                _lesson.value = fetchedLesson
            }
            hideLoading()
        }
    }
}