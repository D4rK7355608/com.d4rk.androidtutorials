package com.d4rk.androidtutorials.ui.screens.home.repository

import android.app.Application
import android.content.Intent
import com.d4rk.androidtutorials.BuildConfig
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.utils.constants.api.ApiConstants
import com.d4rk.androidtutorials.data.core.AppCoreManager
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.api.ApiHomeResponse
import com.d4rk.androidtutorials.data.model.ui.screens.UiHomeLesson
import com.d4rk.androidtutorials.data.model.ui.screens.UiHomeScreen
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

abstract class HomeRepositoryImplementation(
    val application : Application ,
    val dataStore : DataStore ,
) {

    private val client : HttpClient = AppCoreManager.ktorClient

    private val baseUrl : String = BuildConfig.DEBUG.let { isDebug ->
        val environment : String = if (isDebug) "debug" else "release"
        "${ApiConstants.BASE_REPOSITORY_URL}/$environment/en/home/api_get_lessons.json"
    }

    suspend fun getHomeLessonsImplementation() : UiHomeScreen {
        return runCatching {
            val response : HttpResponse = client.get(urlString = baseUrl) {
                contentType(type = ContentType.Application.Json)
            }

            val lessons : List<UiHomeLesson> =
                    response.bodyAsText().takeUnless { text -> text.isBlank() }
                            ?.let { Json.decodeFromString<ApiHomeResponse>(it) }?.data?.map { apiLesson ->
                                UiHomeLesson(lessonId = apiLesson.lessonId ,
                                             lessonTitle = apiLesson.lessonTitle ,
                                             lessonDescription = apiLesson.lessonDescription ,
                                             lessonType = apiLesson.lessonType ,
                                             lessonTags = apiLesson.lessonTags ,
                                             thumbnailImageUrl = apiLesson.thumbnailImageUrl ,
                                             squareImageUrl = apiLesson.squareImageUrl ,
                                             deepLinkPath = apiLesson.deepLinkPath ,
                                             isFavorite = loadFavoritesImplementation().any { it.lessonId == apiLesson.lessonId })
                            } ?: emptyList()
            UiHomeScreen(lessons = ArrayList(lessons))
        }.getOrElse {
            UiHomeScreen()
        }
    }

    suspend fun loadFavoritesImplementation() : List<FavoriteLessonTable> {
        return AppCoreManager.database.favoriteLessonsDao().getAllFavorites()
    }

    suspend fun addLessonToFavoritesImplementation(lesson : FavoriteLessonTable) {
        AppCoreManager.database.favoriteLessonsDao().insert(favoriteLesson = lesson)
    }

    suspend fun removeLessonFromFavoritesImplementation(lesson : FavoriteLessonTable) {
        AppCoreManager.database.favoriteLessonsDao().delete(favoriteLesson = lesson)
    }

    fun shareLessonImplementation(lesson : UiHomeLesson) : Intent {
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