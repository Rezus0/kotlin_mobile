package com.example.messengerandroid.db

import android.content.Context
import androidx.room.Room

object AppDatabaseInstance {
    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "characters"
            ).build()
        }
        return instance!!
    }
}