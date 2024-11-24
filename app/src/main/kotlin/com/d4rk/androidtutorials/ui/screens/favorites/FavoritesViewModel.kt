package com.d4rk.androidtutorials.ui.screens.favorites

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiLesson
import com.d4rk.androidtutorials.ui.screens.home.repository.HomeRepository
import com.d4rk.androidtutorials.ui.viewmodel.LessonsViewModel
import kotlinx.coroutines.launch

class FavoritesViewModel(application : Application) : LessonsViewModel(application) {
    private val repository = HomeRepository(DataStore(application) , application)

    init {
        loadFavorites()
        observeFavorites()
    }

    private fun FavoriteLessonTable.toUiLesson() = UiLesson(
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

    private fun loadFavorites() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showLoading()
            repository.loadFavoritesRepository(onSuccess = { favorites : List<FavoriteLessonTable> ->
                _lessons.value = favorites.map { lessons ->
                    lessons.toUiLesson()
                }
            })
            hideLoading()
            initializeVisibilityStates()
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.observeFavoritesChanges { favorites ->
                _lessons.value = favorites.map { it.toUiLesson() }
            }
        }
    }
}