package com.example.messengerandroid.service

import com.example.messengerandroid.model.Character;

class CharacterRepository {

    private val apiService: ApiService = ApiClient.getClient().create(ApiService::class.java)

    fun getCharacters(page: Int, pageSize: Int, callback: CharacterCallback) {
        apiService.getCharacters(page, pageSize).enqueue(object : retrofit2.Callback<List<Character>> {
            override fun onResponse(
                call: retrofit2.Call<List<Character>>,
                response: retrofit2.Response<List<Character>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onSuccess(response.body()!!)
                } else {
                    callback.onError(Exception("Failed to fetch characters"))
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Character>>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    interface CharacterCallback {
        fun onSuccess(characters: List<Character>)
        fun onError(t: Throwable)
    }
}
