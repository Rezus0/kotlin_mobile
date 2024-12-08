package com.example.messengerandroid.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<Character>)

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<Character>>
}
