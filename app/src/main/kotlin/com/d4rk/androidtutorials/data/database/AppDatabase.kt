package com.d4rk.androidtutorials.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.d4rk.androidtutorials.data.database.converters.Converters
import com.d4rk.androidtutorials.data.database.dao.FavoriteLessonsDao
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable

@Database(entities = [FavoriteLessonTable::class] , version = 1 , exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteLessonsDao() : FavoriteLessonsDao
}