package com.d4rk.androidtutorials.data.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite Lessons")
data class FavoriteLessonTable(
    @PrimaryKey val id : String ,
    val title : String ,
    val description : String ,
    val type : String,
    val bannerImageUrl : String,
    val squareImageUrl : String,
    val deepLinkPath : String,
    val articleType : String,
    val isFavorite : Boolean,
)