package com.d4rk.androidtutorials.data.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.d4rk.androidtutorials.data.database.converters.Converters

@Entity(tableName = "Favorite Lessons")
@TypeConverters(Converters::class)
data class FavoriteLessonTable(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val type: String,
    val tags: List<String>,
    val bannerImageUrl: String,
    val squareImageUrl: String,
    val deepLinkPath: String,
    val articleType: String,
    val isFavorite: Boolean
)