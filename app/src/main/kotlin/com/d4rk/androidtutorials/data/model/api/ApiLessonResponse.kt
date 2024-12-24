package com.d4rk.androidtutorials.data.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiLessonResponse(
    @SerialName("data") val data : List<ApiLesson> = ArrayList()
)

@Serializable
data class ApiLesson(
    @SerialName("lesson_title") val lessonTitle : String = "" ,
    @SerialName("lesson_content") val lessonContent : List<ApiLessonContent> = emptyList()
)

@Serializable
data class ApiLessonContent(
    @SerialName("content_id") val contentId : String = "" ,
    @SerialName("content_type") val contentType : String = "" ,
    @SerialName("content_text") val contentText : String = "" ,
    @SerialName("content_code") val contentCode : String = "" ,
    @SerialName("content_code_programming_language") val programmingLanguage : String = "" ,
    @SerialName("content_image_url") val contentImageUrl : String = "" ,
)
