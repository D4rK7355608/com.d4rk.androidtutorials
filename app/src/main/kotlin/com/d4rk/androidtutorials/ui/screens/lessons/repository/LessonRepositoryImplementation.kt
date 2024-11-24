package com.d4rk.androidtutorials.ui.screens.lessons.repository

import android.app.Application
import com.d4rk.androidtutorials.BuildConfig
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiLessonScreenModel
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

abstract class LessonRepositoryImplementation(
    val application : Application ,
    val dataStore : DataStore ,
) {

    private val baseUrl = if (BuildConfig.DEBUG) {
        "https://raw.githubusercontent.com/D4rK7355608/com.d4rk.apis/refs/heads/main/Android%20Studio%20Tutorials/debug/en/lessons"
    }
    else {
        "https://raw.githubusercontent.com/D4rK7355608/com.d4rk.apis/refs/heads/main/Android%20Studio%20Tutorials/release/en/lessons"
    }

    fun getLessonImplementation(lessonId : String) : UiLessonScreenModel {
        val url = "$baseUrl/api_get_$lessonId.json"
        return URL(url).openConnection().let { apiUrl ->
            apiUrl as HttpURLConnection
            apiUrl.requestMethod = "GET"
            apiUrl.connectTimeout = 5000
            apiUrl.readTimeout = 5000

            when (apiUrl.responseCode) {
                HttpURLConnection.HTTP_OK -> {
                    return@let BufferedReader(InputStreamReader(apiUrl.inputStream)).use { reader ->
                        Gson().fromJson(reader.readText() , UiLessonScreenModel::class.java)
                    }
                }

                // TODO: Add more case handles

                else -> {
                    return@let UiLessonScreenModel()
                }
            }
        }
    }
}