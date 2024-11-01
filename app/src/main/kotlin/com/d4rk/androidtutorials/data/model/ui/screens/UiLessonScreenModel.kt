package com.d4rk.androidtutorials.data.model.ui.screens

import kotlinx.serialization.SerialName

data class UiLessonScreenModel(
    @SerialName("title") val title: String = "" ,
    @SerialName("content") val content: List<UiLessonContentModel> = emptyList()
)

data class UiLessonContentModel(
    @SerialName("id") val id: String = "",
    @SerialName("type") val type: String = "",
    @SerialName("text") val text: String = "",
    @SerialName("language") val language: String = "",
    @SerialName("code") val code: String = "",
    @SerialName("url") val url: String = "",
    @SerialName("bannerImageUrl") val src: String = ""
)