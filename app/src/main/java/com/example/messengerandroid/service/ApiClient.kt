package com.example.messengerandroid.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://www.anapioficeandfire.com/api/"
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        return retrofit ?: Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().also { retrofit = it }
    }
}