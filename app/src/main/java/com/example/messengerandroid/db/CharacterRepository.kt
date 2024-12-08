package com.example.messengerandroid.db

import android.util.Log
import kotlinx.coroutines.flow.Flow

class CharacterRepository(private val characterDao: CharacterDao) {

    fun getAllCharacters(): Flow<List<Character>> {
        return characterDao.getAllCharacters()
    }

    suspend fun clearCharacters() {
        characterDao.deleteAllCharacters()
    }

    suspend fun saveCharactersIntoDb(characters: List<Character>) {
        try {
            clearCharacters()
            characterDao.insertCharacters(characters)
        } catch (e: Exception) {
            Log.e("TAG", "Error saving data to database", e)
        }
    }
}
