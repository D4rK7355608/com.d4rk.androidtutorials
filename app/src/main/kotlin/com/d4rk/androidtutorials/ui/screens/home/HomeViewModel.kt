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

    private val _favorites = MutableStateFlow<List<FavoriteLessonTable>>(emptyList())
    val favorites : StateFlow<List<FavoriteLessonTable>> = _favorites.asStateFlow()

    init {
        getHomeLessons()
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

    fun toggleFavorite(lesson: UiLesson) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val updatedLesson = lesson.copy(favorite = !lesson.favorite)
            val updatedLessons = _lessons.value.map {
                if (it.id == updatedLesson.id) updatedLesson else it
            }
            _lessons.value = updatedLessons

            if (updatedLesson.favorite) {
                addLessonToFavorites(updatedLesson)
            } else {
                removeLessonFromFavorites(updatedLesson)
            }
        }
    }

    fun addLessonToFavorites(lesson : UiLesson) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val favoriteLesson = FavoriteLessonTable(
                id = lesson.id,
                title = lesson.title,
                description = lesson.description,
                type = lesson.type,
                bannerImageUrl = lesson.bannerImageUrl,
                squareImageUrl = lesson.squareImageUrl,
                deepLinkPath = lesson.deepLinkPath,
                articleType = lesson.articleType,
                isFavorite = lesson.favorite,
            )
            repository.addLessonToFavoritesRepository(favoriteLesson) {

            }
        }
    }

    fun removeLessonFromFavorites(lesson : UiLesson) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val favoriteLesson = FavoriteLessonTable(
                id = lesson.id,
                title = lesson.title,
                description = lesson.description,
                type = lesson.type,
                bannerImageUrl = lesson.bannerImageUrl,
                squareImageUrl = lesson.squareImageUrl,
                deepLinkPath = lesson.deepLinkPath,
                articleType = lesson.articleType,
                isFavorite = lesson.favorite,
            )
            repository.removeLessonFromFavoritesRepository(favoriteLesson) {

            }
        }
    }
}