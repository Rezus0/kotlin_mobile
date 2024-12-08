package com.example.messengerandroid.service

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "app_preferences")

val NICKNAME_KEY = stringPreferencesKey("nickname_key")

suspend fun saveNickname(context: Context, nickname: String) {
    context.dataStore.edit { preferences ->
        preferences[NICKNAME_KEY] = nickname
    }
}

suspend fun getNickname(context: Context): String {
    return context.dataStore.data.first()[NICKNAME_KEY] ?: ""
}
