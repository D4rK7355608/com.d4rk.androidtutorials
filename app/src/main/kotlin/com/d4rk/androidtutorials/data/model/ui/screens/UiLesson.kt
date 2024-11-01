package com.d4rk.androidtutorials.data.model.ui.screens

import com.google.gson.annotations.SerializedName

data class UiLesson(
    @SerializedName("id") val id : String = "" ,
    @SerializedName("title") var title : String = "" ,
    @SerializedName("description") var description : String = "" ,
    @SerializedName("type") var type : String = "" ,
    @SerializedName("tags") var tags : List<String> = emptyList() ,
    @SerializedName("bannerImageUrl") var bannerImageUrl : String = "" ,
    @SerializedName("squareImageUrl") var squareImageUrl : String = "" ,
    @SerializedName("deepLinkPath") var deepLinkPath : String = "" ,
    @SerializedName("articleType") var articleType : String = "" ,
    var favorite: Boolean = false
)
