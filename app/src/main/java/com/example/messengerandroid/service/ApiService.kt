package com.example.messengerandroid.service

import com.example.messengerandroid.db.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("characters")
    fun getCharacters(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Call<List<Character>>
}