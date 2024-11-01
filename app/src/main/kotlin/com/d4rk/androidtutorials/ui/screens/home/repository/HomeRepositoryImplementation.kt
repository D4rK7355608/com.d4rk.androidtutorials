package com.d4rk.androidtutorials.ui.screens.home.repository

import android.app.Application
import com.d4rk.androidtutorials.BuildConfig
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

    fun getHomeLessonsImplementation() : List<UiLesson>? {
        val url = "$baseUrl/api_get_lessons.json"
        return URL(url).openConnection().let {
            it as HttpURLConnection
            it.requestMethod = "GET"
            it.connectTimeout = 5000
            it.readTimeout = 5000

            when (it.responseCode) {
                HttpURLConnection.HTTP_OK -> {
                    BufferedReader(InputStreamReader(it.inputStream)).use { reader ->
                        val type = object : TypeToken<List<UiLesson>>() {}.type
                        Gson().fromJson<List<UiLesson>>(reader.readText() , type)
                    }
                }

                // TODO: Add more case handles

                else -> {
                    null
                }
            }
        }
    }
}