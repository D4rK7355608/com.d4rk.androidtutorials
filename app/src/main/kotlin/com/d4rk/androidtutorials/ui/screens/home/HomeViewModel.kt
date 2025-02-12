package com.d4rk.androidtutorials.ui.screens.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiHomeLesson
import com.d4rk.androidtutorials.data.model.ui.screens.UiHomeScreen
import com.d4rk.androidtutorials.ui.screens.home.repository.HomeRepository
import com.d4rk.androidtutorials.ui.viewmodel.LessonsViewModel
import com.d4rk.androidtutorials.utils.extensions.toFavoriteLessonTable
import kotlinx.coroutines.launch

class HomeViewModel(application : Application) : LessonsViewModel(application) {
    private val repository : HomeRepository = HomeRepository(DataStore(application) , application)

    init {
        getHomeLessons()
        observeFavorites()
    }

    fun getHomeLessons() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            showLoading()
            repository.getHomeLessonsRepository { fetchedLessons ->
                _lessons.value = listOf(element = fetchedLessons)
            }
            hideLoading()
            initializeVisibilityStates()
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            repository.observeFavoritesChanges { favorites ->
                val updatedLessons = _lessons.value.firstOrNull()?.lessons?.map { lesson ->
                    lesson.copy(isFavorite = favorites.any { it.lessonId == lesson.lessonId })
                } ?: emptyList()

                _lessons.value = listOf(element = UiHomeScreen(lessons = ArrayList(updatedLessons)))
            }
        }
    }

    fun toggleFavorite(lesson : UiHomeLesson) {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            val updatedLesson = lesson.copy(isFavorite = ! lesson.isFavorite)
            val updatedLessons = _lessons.value.firstOrNull()?.lessons?.map {
                if (it.lessonId == updatedLesson.lessonId) updatedLesson else it
            } ?: emptyList()

            _lessons.value = listOf(element = UiHomeScreen(lessons = ArrayList(updatedLessons)))

            if (updatedLesson.isFavorite) {
                addLessonToFavorites(lesson = updatedLesson)
            }
            else {
                removeLessonFromFavorites(lesson = updatedLesson)
            }
        }
    }

    private fun addLessonToFavorites(lesson : UiHomeLesson) {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            val favoriteLesson = lesson.toFavoriteLessonTable()
            repository.addLessonToFavoritesRepository(lesson = favoriteLesson) {}
        }
    }

    private fun removeLessonFromFavorites(lesson : UiHomeLesson) {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            val favoriteLesson = lesson.toFavoriteLessonTable()
            repository.removeLessonFromFavoritesRepository(lesson = favoriteLesson) {}
        }
    }

    fun shareLesson(lesson : UiHomeLesson) {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            repository.shareLessonRepository(lesson = lesson)
        }
    }
}