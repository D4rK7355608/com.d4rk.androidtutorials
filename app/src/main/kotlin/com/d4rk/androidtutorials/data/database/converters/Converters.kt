package com.d4rk.androidtutorials.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromStringList(value : List<String>) : String {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().toJson(value , type)
    }

    @TypeConverter
    fun toStringList(value : String) : List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value , type)
    }
}
