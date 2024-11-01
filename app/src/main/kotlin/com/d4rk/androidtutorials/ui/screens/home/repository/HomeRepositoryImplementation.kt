package com.d4rk.androidtutorials.ui.screens.home.repository

import android.app.Application
import com.d4rk.androidtutorials.BuildConfig
import com.d4rk.androidtutorials.data.core.AppCoreManager
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiLesson
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

abstract class HomeRepositoryImplementation(
    val application : Application ,
    val dataStore : DataStore ,
) {

    private val baseUrl = if (BuildConfig.DEBUG) {
        "https://raw.githubusercontent.com/D4rK7355608/com.d4rk.apis/refs/heads/main/Android%20Studio%20Tutorials/debug/en/home"
    }
    else {
        "https://raw.githubusercontent.com/D4rK7355608/com.d4rk.apis/refs/heads/main/Android%20Studio%20Tutorials/release/en/home"
    }

    suspend fun getHomeLessonsImplementation() : List<UiLesson>? {
        val url = "$baseUrl/api_get_lessons.json"
        val lessons : List<UiLesson>?

        @Suppress("BlockingMethodInNonBlockingContext") URL(url).openConnection()
                .let { urlConnection ->
                    urlConnection as HttpURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.connectTimeout = 5000
                    urlConnection.readTimeout = 5000

                    when (urlConnection.responseCode) {
                        HttpURLConnection.HTTP_OK -> {
                            BufferedReader(InputStreamReader(urlConnection.inputStream)).use { reader ->
                                val type = object : TypeToken<List<UiLesson>>() {}.type
                                lessons = Gson().fromJson<List<UiLesson>>(reader.readText() , type)
                            }
                        }

                        // TODO: Handle more cases if needed
                        else -> {
                            return null
                        }
                    }
                }

        return lessons?.map { lesson ->
            lesson.copy(favorite = loadFavoritesImplementation().any { it.id == lesson.id })
        }
    }

    private suspend fun loadFavoritesImplementation() : List<FavoriteLessonTable> {
        return AppCoreManager.database.favoriteLessonsDao().getAllFavorites()
    }

    suspend fun addLessonToFavoritesImplementation(lesson : FavoriteLessonTable) {
        AppCoreManager.database.favoriteLessonsDao().insert(lesson)
    }

    suspend fun removeLessonFromFavoritesImplementation(lesson : FavoriteLessonTable) {
        AppCoreManager.database.favoriteLessonsDao().delete(lesson)
    }
}