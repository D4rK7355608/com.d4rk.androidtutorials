package com.d4rk.androidtutorials.ui.screens.favorites

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiLesson
import com.d4rk.androidtutorials.ui.screens.favorites.repository.FavoritesRepository
import com.d4rk.androidtutorials.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : BaseViewModel(application) {
    private val repository = FavoritesRepository(DataStore(application), application)
    private val _favoriteLessons = MutableLiveData<List<UiLesson>>()
    val favoriteLessons: LiveData<List<UiLesson>> get() = _favoriteLessons

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showLoading()
            repository.loadFavoritesRepository(
                onSuccess = { favorites: List<FavoriteLessonTable> ->
                    _favoriteLessons.value = favorites.map { favoriteLesson ->
                        UiLesson(
                            id = favoriteLesson.id,
                            title = favoriteLesson.title,
                            description = favoriteLesson.description,
                            type = favoriteLesson.type,
                            bannerImageUrl = favoriteLesson.bannerImageUrl,
                            squareImageUrl = favoriteLesson.squareImageUrl,
                            deepLinkPath = favoriteLesson.deepLinkPath,
                            articleType = favoriteLesson.articleType,
                            favorite = favoriteLesson.isFavorite,
                        )
                    }
                }
            )
            hideLoading()
        }
    }
}
