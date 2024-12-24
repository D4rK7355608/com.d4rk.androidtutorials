package com.d4rk.androidtutorials.ui.screens.favorites

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiHomeLesson
import com.d4rk.androidtutorials.data.model.ui.screens.UiHomeScreen
import com.d4rk.androidtutorials.ui.screens.home.repository.HomeRepository
import com.d4rk.androidtutorials.ui.viewmodel.LessonsViewModel
import com.d4rk.androidtutorials.utils.extensions.toUiLesson
import kotlinx.coroutines.launch

class FavoritesViewModel(application : Application) : LessonsViewModel(application) {
    private val repository = HomeRepository(DataStore(application) , application)

    init {
        loadFavorites()
        observeFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            showLoading()
            repository.loadFavoritesRepository { favorites ->
                val favoriteLessons = favorites.map { lesson ->
                    lesson.toUiLesson()
                }
                listOf(UiHomeScreen(lessons = ArrayList(favoriteLessons))).also {
                    _lessons.value = it
                }
            }
            hideLoading()
            initializeVisibilityStates()
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            repository.observeFavoritesChanges { favorites ->
                val favoriteLessons = favorites.map { lesson ->
                    lesson.toUiLesson()
                }
                _lessons.value =
                        listOf(element = UiHomeScreen(lessons = ArrayList(favoriteLessons)))
            }
        }
    }
}