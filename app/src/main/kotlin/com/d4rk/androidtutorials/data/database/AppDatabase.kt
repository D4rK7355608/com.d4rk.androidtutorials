package com.d4rk.androidtutorials.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.d4rk.androidtutorials.data.database.dao.FavoriteLessonsDao
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable

@Database(entities = [FavoriteLessonTable::class] , version = 3 , exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteLessonsDao() : FavoriteLessonsDao
}