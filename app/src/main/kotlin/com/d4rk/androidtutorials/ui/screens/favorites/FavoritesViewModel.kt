package com.d4rk.androidtutorials.ui.screens.favorites

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiHomeLesson
import com.d4rk.androidtutorials.data.model.ui.screens.UiHomeScreen
import com.d4rk.androidtutorials.ui.screens.home.repository.HomeRepository
import com.d4rk.androidtutorials.ui.viewmodel.LessonsViewModel
import kotlinx.coroutines.launch

class FavoritesViewModel(application : Application) : LessonsViewModel(application) {
    private val repository = HomeRepository(DataStore(application) , application)

    init {
        loadFavorites()
        observeFavorites()
    }

    private fun FavoriteLessonTable.toUiLesson() : UiHomeLesson {
        return UiHomeLesson(
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
    }

    private fun loadFavorites() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showLoading()
            repository.loadFavoritesRepository { favorites ->
                val favoriteLessons = favorites.map { it.toUiLesson() }
                _lessons.value = listOf(UiHomeScreen(lessons = ArrayList(favoriteLessons)))
            }
            hideLoading()
            initializeVisibilityStates()
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.observeFavoritesChanges { favorites ->
                val favoriteLessons = favorites.map { it.toUiLesson() }
                _lessons.value = listOf(UiHomeScreen(lessons = ArrayList(favoriteLessons)))
            }
        }
    }
}