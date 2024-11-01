package com.d4rk.androidtutorials.ui.screens.favorites.repository

import android.app.Application
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import com.d4rk.androidtutorials.data.datastore.DataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritesRepository(dataStore : DataStore , application : Application) :
    FavoritesRepositoryImplementation(application , dataStore) {

    suspend fun loadFavoritesRepository(onSuccess: (List<FavoriteLessonTable>) -> Unit) {
        withContext(Dispatchers.IO) {
            val favorites = loadFavoritesImplementation()
            withContext(Dispatchers.Main) {
                onSuccess(favorites)
            }
        }
    }
}