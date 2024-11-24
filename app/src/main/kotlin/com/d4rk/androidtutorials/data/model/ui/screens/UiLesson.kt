package com.d4rk.androidtutorials.data.model.ui.screens

import com.google.gson.annotations.SerializedName

data class UiLesson(
    @SerializedName("lesson_id") val lessonId : String = "" ,
    @SerializedName("lesson_title") var lessonTitle : String = "" ,
    @SerializedName("lesson_description") var lessonDescription : String = "" ,
    @SerializedName("lesson_type") var lessonType : String = "" ,
    @SerializedName("lesson_tags") var lessonTags : List<String> = emptyList() ,
    @SerializedName("thumbnail_image_url") var thumbnailImageUrl : String = "" ,
    @SerializedName("square_image_url") var squareImageUrl : String = "" ,
    @SerializedName("deep_link_path") var deepLinkPath : String = "" ,
    var isFavorite : Boolean = false
)