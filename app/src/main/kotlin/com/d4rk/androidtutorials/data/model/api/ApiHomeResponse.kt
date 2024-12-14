package com.d4rk.androidtutorials.data.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiHomeResponse(
    @SerialName("data") val data: List<ApiHomeLessons> = ArrayList()
)

@Serializable
data class ApiHomeLessons(
    @SerialName("lesson_id") val lessonId: String = "",
    @SerialName("lesson_title") var lessonTitle: String = "",
    @SerialName("lesson_description") var lessonDescription: String = "",
    @SerialName("lesson_type") var lessonType: String = "",
    @SerialName("lesson_tags") var lessonTags: List<String> = emptyList(),
    @SerialName("thumbnail_image_url") var thumbnailImageUrl: String = "",
    @SerialName("square_image_url") var squareImageUrl: String = "",
    @SerialName("deep_link_path") var deepLinkPath: String = ""
)