package com.d4rk.androidtutorials.data.model.ui.screens

import com.google.gson.annotations.SerializedName

data class UiLessonScreenModel(
    @SerializedName("lesson_title") val lessonTitle : String = "" ,
    @SerializedName("lesson_content") val lessonContent : List<UiLessonContentModel> = emptyList()
)

data class UiLessonContentModel(
    @SerializedName("content_id") val contentId : String = "" ,
    @SerializedName("content_type") val contentType : String = "" ,
    @SerializedName("content_text") val contentText : String = "" ,
    @SerializedName("content_code") val contentCode : String = "" ,
    @SerializedName("content_code_programming_language") val programmingLanguage : String = "" ,
    @SerializedName("content_image_src") val contentImageSrc : String = ""
)