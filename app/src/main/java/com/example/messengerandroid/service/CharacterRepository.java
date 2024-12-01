package com.example.messengerandroid.service;

import androidx.annotation.NonNull;

import com.example.messengerandroid.model.Character;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepository {

    private final ApiService apiService;

    public CharacterRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void getCharacters(int page, int pageSize, CharacterCallback callback) {
        apiService.getCharacters(page, pageSize).enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse(@NonNull Call<List<Character>> call,
                                   @NonNull Response<List<Character>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(new Exception("Failed to fetch characters"));
                }
            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public interface CharacterCallback {
        void onSuccess(List<Character> characters);
        void onError(Throwable t);
    }
}

