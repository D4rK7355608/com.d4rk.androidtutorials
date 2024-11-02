package com.d4rk.androidtutorials.ui.screens.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiLesson
import com.d4rk.androidtutorials.ui.screens.home.repository.HomeRepository
import com.d4rk.androidtutorials.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application : Application) : BaseViewModel(application) {
    private val repository = HomeRepository(DataStore(application) , application)
    private val _lessons = MutableStateFlow<List<UiLesson>>(emptyList())
    val lessons : StateFlow<List<UiLesson>> = _lessons.asStateFlow()

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
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.observeFavoritesChanges { favorites ->
                val updatedLessons = _lessons.value.map { lesson ->
                    lesson.copy(favorite = favorites.any { it.id == lesson.id })
                }
                _lessons.value = updatedLessons
            }
        }
    }

    fun toggleFavorite(lesson : UiLesson) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val updatedLesson = lesson.copy(favorite = ! lesson.favorite)
            val updatedLessons = _lessons.value.map { lesson ->
                if (lesson.id == updatedLesson.id) updatedLesson else lesson
            }
            _lessons.value = updatedLessons

            if (updatedLesson.favorite) {
                addLessonToFavorites(updatedLesson)
            }
            else {
                removeLessonFromFavorites(updatedLesson)
            }
        }
    }

    private fun UiLesson.toFavoriteLessonTable() : FavoriteLessonTable = FavoriteLessonTable(
        id = id ,
        title = title ,
        description = description ,
        type = type ,
        bannerImageUrl = bannerImageUrl ,
        squareImageUrl = squareImageUrl ,
        deepLinkPath = deepLinkPath ,
        articleType = articleType ,
        tags = tags ,
        isFavorite = favorite
    )

    private fun addLessonToFavorites(lesson : UiLesson) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val favoriteLesson = lesson.toFavoriteLessonTable()
            repository.addLessonToFavoritesRepository(favoriteLesson) {

            }
        }
    }

    private fun removeLessonFromFavorites(lesson : UiLesson) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val favoriteLesson = lesson.toFavoriteLessonTable()
            repository.removeLessonFromFavoritesRepository(favoriteLesson) {

            }
        }
    }
}