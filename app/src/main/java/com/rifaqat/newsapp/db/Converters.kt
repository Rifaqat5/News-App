package com.rifaqat.newsapp.db

import androidx.room.TypeConverter
import com.rifaqat.newsapp.models.Source


class Converters {
    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }

    @TypeConverter
    fun fromSource(name: String):Source{
        return Source(name,name)
    }
}