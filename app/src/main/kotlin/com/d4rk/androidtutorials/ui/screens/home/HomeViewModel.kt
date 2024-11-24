package com.d4rk.androidtutorials.ui.screens.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiLesson
import com.d4rk.androidtutorials.ui.screens.home.repository.HomeRepository
import com.d4rk.androidtutorials.ui.viewmodel.LessonsViewModel
import kotlinx.coroutines.launch

class HomeViewModel(application : Application) : LessonsViewModel(application) {
    private val repository = HomeRepository(DataStore(application) , application)

    init {
        getHomeLessons()
        observeFavorites()
    }

    private fun getHomeLessons() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showLoading()
            repository.getHomeLessonsRepository { fetchedLessons ->
                _lessons.value = fetchedLessons
            }
            hideLoading()
            initializeVisibilityStates()
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.observeFavoritesChanges { favorites ->
                val updatedLessons = _lessons.value.map { lesson ->
                    lesson.copy(isFavorite = favorites.any { it.lessonId == lesson.lessonId })
                }
                _lessons.value = updatedLessons
            }
        }
    }

    fun toggleFavorite(lesson : UiLesson) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val updatedLesson = lesson.copy(isFavorite = ! lesson.isFavorite)
            val updatedLessons = _lessons.value.map { lesson ->
                if (lesson.lessonId == updatedLesson.lessonId) updatedLesson else lesson
            }
            _lessons.value = updatedLessons

            if (updatedLesson.isFavorite) {
                addLessonToFavorites(updatedLesson)
            }
            else {
                removeLessonFromFavorites(updatedLesson)
            }
        }
    }

    private fun UiLesson.toFavoriteLessonTable() : FavoriteLessonTable = FavoriteLessonTable(
        lessonId = lessonId ,
        lessonTitle = lessonTitle ,
        lessonDescription = lessonDescription ,
        lessonType = lessonType ,
        thumbnailImageUrl = thumbnailImageUrl ,
        squareImageUrl = squareImageUrl ,
        deepLinkPath = deepLinkPath ,
        lessonTags = lessonTags ,
        isFavorite = isFavorite
    )

    private fun addLessonToFavorites(lesson : UiLesson) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val favoriteLesson = lesson.toFavoriteLessonTable()
            repository.addLessonToFavoritesRepository(favoriteLesson) { }
        }
    }

    private fun removeLessonFromFavorites(lesson : UiLesson) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val favoriteLesson = lesson.toFavoriteLessonTable()
            repository.removeLessonFromFavoritesRepository(favoriteLesson) { }
        }
    }

    fun shareLesson(lesson : UiLesson) {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.shareLessonRepository(
                lesson = lesson ,
            )
        }
    }
}