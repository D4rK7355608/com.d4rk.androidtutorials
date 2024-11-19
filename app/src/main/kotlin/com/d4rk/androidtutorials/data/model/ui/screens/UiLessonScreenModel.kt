package com.d4rk.androidtutorials.data.model.ui.screens

import com.google.gson.annotations.SerializedName

data class UiLessonScreenModel(
    @SerializedName("title") val title : String = "" ,
    @SerializedName("content") val content : List<UiLessonContentModel> = emptyList()
)

data class UiLessonContentModel(
    @SerializedName("id") val id : String = "" ,
    @SerializedName("type") val type : String = "" ,
    @SerializedName("text") val text : String = "" ,
    @SerializedName("language") val language : String = "" ,
    @SerializedName("code") val code : String = "" ,
    @SerializedName("url") val url : String = "" ,
    @SerializedName("src") val src : String = ""
)