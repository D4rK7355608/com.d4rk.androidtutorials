package com.d4rk.androidtutorials.ui.screens.home.repository

import android.app.Application
import android.content.Intent
import com.d4rk.androidtutorials.BuildConfig
import com.d4rk.androidtutorials.R
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

    private val type = object : TypeToken<ArrayList<UiLesson>>() {}.type

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
            lesson.copy(isFavorite = loadFavoritesImplementation().any { it.lessonId == lesson.lessonId })
        }
    }

    suspend fun loadFavoritesImplementation() : List<FavoriteLessonTable> {
        return AppCoreManager.database.favoriteLessonsDao().getAllFavorites()
    }

    suspend fun addLessonToFavoritesImplementation(lesson : FavoriteLessonTable) {
        AppCoreManager.database.favoriteLessonsDao().insert(lesson)
    }

    suspend fun removeLessonFromFavoritesImplementation(lesson : FavoriteLessonTable) {
        AppCoreManager.database.favoriteLessonsDao().delete(lesson)
    }

    fun shareLessonImplementation(lesson : UiLesson) : Intent {
        return Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT , buildString {
                append(lesson.lessonDescription)
                append("\n\n")
                append(
                    application.getString(
                        R.string.get_the_app_to_watch ,
                        "https://play.google.com/store/apps/details?id=${application.packageName}"
                    )
                )
            })
            putExtra(
                Intent.EXTRA_SUBJECT ,
                application.getString(R.string.share_lesson_subject , lesson.lessonTitle)
            )
        }
    }
}