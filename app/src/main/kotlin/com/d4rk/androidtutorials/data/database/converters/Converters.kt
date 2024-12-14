package com.d4rk.androidtutorials.data.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class Converters {
    @TypeConverter
    fun fromStringList(value : List<String>) : String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toStringList(value : String) : List<String> {
        return Json.decodeFromString(value)
    }
}