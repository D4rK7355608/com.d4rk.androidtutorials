package com.d4rk.androidtutorials.ui.screens.home.repository

import android.app.Application
import android.content.Intent
import android.net.Uri
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
            lesson.copy(favorite = loadFavoritesImplementation().any { it.id == lesson.id })
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

    fun shareLessonImplementation(lesson: UiLesson): Intent {
        return Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check out this lesson: ${lesson.deepLinkPath}")
            putExtra(Intent.EXTRA_SUBJECT, "Discover a New Lesson on ${lesson.title}")

            val imageUrl = lesson.bannerImageUrl.ifEmpty { lesson.squareImageUrl }

            if (imageUrl.isNotEmpty()) {
                val uri = Uri.parse(imageUrl)
                putExtra(Intent.EXTRA_STREAM, uri)
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                type = "image/*"
            } else {
                type = "text/plain"
            }
        }
    }
}