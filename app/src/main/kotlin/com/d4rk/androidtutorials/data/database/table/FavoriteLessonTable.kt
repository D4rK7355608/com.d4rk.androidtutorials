package com.d4rk.androidtutorials.data.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.d4rk.androidtutorials.data.database.converters.Converters

@Entity(tableName = "Favorite Lessons")
@TypeConverters(Converters::class)
data class FavoriteLessonTable(
    @PrimaryKey val lessonId : String ,
    val lessonTitle : String ,
    val lessonDescription : String ,
    val lessonType : String ,
    val lessonTags : List<String> ,
    val thumbnailImageUrl : String ,
    val squareImageUrl : String ,
    val deepLinkPath : String ,
    val isFavorite : Boolean
)