package com.d4rk.androidtutorials.ui.screens.favorites

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class FavoritesViewModel(application : Application) : BaseViewModel(application) {

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showLoading()

            // hideLoading()
        }
    }
}