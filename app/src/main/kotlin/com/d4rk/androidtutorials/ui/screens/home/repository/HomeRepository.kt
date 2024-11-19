package com.d4rk.androidtutorials.ui.screens.home.repository

import android.app.Application
import android.content.Intent
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.data.core.AppCoreManager
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiLesson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository class for handling home screen data and operations.
 *
 * @param dataStore The DataStore instance for accessing user preferences.
 * @param application The application instance for accessing resources and external files directory.
 * @author Mihai-Cristian Condrea
 */
class HomeRepository(
    dataStore : DataStore , application : Application ,
) : HomeRepositoryImplementation(application , dataStore) {

    suspend fun getHomeLessonsRepository(onSuccess : (List<UiLesson>) -> Unit) {
        withContext(Dispatchers.IO) {
            val lessons = getHomeLessonsImplementation()
            return@withContext withContext(Dispatchers.Main) {
                lessons?.let { onSuccess(it) }
            }
        }
    }

    suspend fun addLessonToFavoritesRepository(
        lesson : FavoriteLessonTable ,
        onSuccess : () -> Unit ,
    ) {
        withContext(Dispatchers.IO) {
            addLessonToFavoritesImplementation(lesson)
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    suspend fun loadFavoritesRepository(onSuccess : (List<FavoriteLessonTable>) -> Unit) {
        withContext(Dispatchers.IO) {
            val favorites = loadFavoritesImplementation()
            withContext(Dispatchers.Main) {
                onSuccess(favorites)
            }
        }
    }

    suspend fun observeFavoritesChanges(onFavoritesChanged : (List<FavoriteLessonTable>) -> Unit) {
        AppCoreManager.database.favoriteLessonsDao().getAllFavoritesFlow().collect { favorites ->
            withContext(Dispatchers.Main) {
                onFavoritesChanged(favorites)
            }
        }
    }

    suspend fun removeLessonFromFavoritesRepository(
        lesson : FavoriteLessonTable ,
        onSuccess : () -> Unit ,
    ) {
        withContext(Dispatchers.IO) {
            removeLessonFromFavoritesImplementation(lesson)
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    suspend fun shareLessonRepository(lesson : UiLesson) {
        withContext(Dispatchers.IO) {
            val shareIntent : Intent = shareLessonImplementation(lesson)
            withContext(Dispatchers.Main) {
                val chooserIntent : Intent = Intent.createChooser(
                    shareIntent , application.getString(R.string.share)
                )
                chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                application.startActivity(chooserIntent)
            }
        }
    }
}