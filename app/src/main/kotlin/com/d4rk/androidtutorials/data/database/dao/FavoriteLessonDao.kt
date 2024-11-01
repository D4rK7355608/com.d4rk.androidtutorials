package com.d4rk.androidtutorials.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable

@Dao
interface FavoriteLessonsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteLesson: FavoriteLessonTable)

    @Delete
    suspend fun delete(favoriteLesson: FavoriteLessonTable)

    @Query("SELECT * FROM `Favorite Lessons`")
    suspend fun getAllFavorites(): List<FavoriteLessonTable>

    @Query("SELECT COUNT(*) FROM `Favorite Lessons` WHERE id = :lessonId")
    suspend fun isFavorite(lessonId: String): Int
}