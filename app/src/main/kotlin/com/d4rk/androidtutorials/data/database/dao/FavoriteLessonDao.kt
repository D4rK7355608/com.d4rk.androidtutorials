package com.d4rk.androidtutorials.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteLessonsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteLesson : FavoriteLessonTable)

    @Delete
    suspend fun delete(favoriteLesson : FavoriteLessonTable)

    @Query("SELECT * FROM `favorite lessons`")
    suspend fun getAllFavorites() : List<FavoriteLessonTable>


    @Query("SELECT * FROM `Favorite Lessons`")
    fun getAllFavoritesFlow() : Flow<List<FavoriteLessonTable>>

    @Query("SELECT COUNT(*) FROM `favorite lessons` WHERE lessonId = :lessonId")
    suspend fun isFavorite(lessonId : String) : Int
}