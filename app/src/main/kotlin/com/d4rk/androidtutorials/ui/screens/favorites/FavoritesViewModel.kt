package com.d4rk.androidtutorials.ui.screens.favorites

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiLesson
import com.d4rk.androidtutorials.ui.screens.home.repository.HomeRepository
import com.d4rk.androidtutorials.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class FavoritesViewModel(application : Application) : BaseViewModel(application) {
    private val repository = HomeRepository(DataStore(application) , application)
    private val _favoriteLessons = MutableLiveData<List<UiLesson>>()
    val favoriteLessons : LiveData<List<UiLesson>> get() = _favoriteLessons

    init {
        loadFavorites()
        observeFavorites()
    }

    private fun FavoriteLessonTable.toUiLesson() = UiLesson(
        id = id ,
        title = title ,
        description = description ,
        type = type ,
        bannerImageUrl = bannerImageUrl ,
        squareImageUrl = squareImageUrl ,
        deepLinkPath = deepLinkPath ,
        articleType = articleType ,
        tags = tags ,
        favorite = isFavorite
    )

    private fun loadFavorites() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showLoading()
            repository.loadFavoritesRepository(onSuccess = { favorites : List<FavoriteLessonTable> ->
                _favoriteLessons.value = favorites.map { it.toUiLesson() }
            })
            hideLoading()
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.observeFavoritesChanges { favorites ->
                _favoriteLessons.value = favorites.map { it.toUiLesson() }
            }
        }
    }
}
