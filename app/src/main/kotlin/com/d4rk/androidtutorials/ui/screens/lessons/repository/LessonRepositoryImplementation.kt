package com.d4rk.androidtutorials.ui.screens.lessons.repository

import com.d4rk.androidtutorials.BuildConfig
import com.d4rk.androidtutorials.utils.constants.api.ApiConstants
import com.d4rk.androidtutorials.data.core.AppCoreManager
import com.d4rk.androidtutorials.data.model.api.ApiLessonResponse
import com.d4rk.androidtutorials.data.model.ui.screens.UiLessonContent
import com.d4rk.androidtutorials.data.model.ui.screens.UiLessonScreen
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

abstract class LessonRepositoryImplementation {

    private val client : HttpClient = AppCoreManager.ktorClient

    private val baseUrl = BuildConfig.DEBUG.let { isDebug ->
        val environment = if (isDebug) "debug" else "release"
        "${ApiConstants.BASE_REPOSITORY_URL}/$environment/en/lessons"
    }

    suspend fun getLessonImplementation(lessonId : String) : UiLessonScreen {
        val url = "$baseUrl/api_get_$lessonId.json"

        return runCatching {
            val response : HttpResponse = client.get(url) {
                contentType(ContentType.Application.Json)
            }

            val lessonScreen : UiLessonScreen = response.bodyAsText().takeUnless { it.isBlank() }
                    ?.let { Json.decodeFromString<ApiLessonResponse>(it) }?.data?.firstOrNull()
                    ?.let { apiLesson ->
                        UiLessonScreen(lessonTitle = apiLesson.lessonTitle ,
                                       lessonContent = ArrayList(apiLesson.lessonContent.map { apiContent ->
                                           UiLessonContent(
                                               contentId = apiContent.contentId ,
                                               contentType = apiContent.contentType ,
                                               contentText = apiContent.contentText ,
                                               contentImageUrl = apiContent.contentImageUrl ,
                                               contentCode = apiContent.contentCode ,
                                               programmingLanguage = apiContent.programmingLanguage
                                           )
                                       })
                        )
                    } ?: UiLessonScreen()
            lessonScreen
        }.getOrElse {
            UiLessonScreen()
        }
    }
}