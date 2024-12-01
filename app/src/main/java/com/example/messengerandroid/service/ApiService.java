package com.example.messengerandroid.service;

import com.example.messengerandroid.model.Character;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface ApiService {
    @GET("characters")
    Call<List<Character>> getCharacters(@Query("page") int page, @Query("pageSize") int pageSize);
}

