package com.d4rk.androidtutorials.ui.screens.favorites.repository

import android.app.Application
import com.d4rk.androidtutorials.data.core.AppCoreManager
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import com.d4rk.androidtutorials.data.datastore.DataStore

abstract class FavoritesRepositoryImplementation(
    val application : Application ,
    val dataStore : DataStore ,
) {

    suspend fun loadFavoritesImplementation(): List<FavoriteLessonTable> {
        return AppCoreManager.database.favoriteLessonsDao().getAllFavorites()
    }
}