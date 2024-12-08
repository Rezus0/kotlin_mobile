package com.example.messengerandroid.db

import androidx.room.TypeConverter

class ListToStringConverter {
    @TypeConverter
    fun listToString(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun stringToList(string: String): List<String> {
        return string.split(",")
    }
}