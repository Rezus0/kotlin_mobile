package com.example.messengerandroid.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.messengerandroid.databinding.ActivityCharacterBinding;
import com.example.messengerandroid.model.Character;
import com.example.messengerandroid.model.CharacterAdapter;
import com.example.messengerandroid.service.CharacterRepository;

import java.util.List;

public class CharacterFragment extends Fragment {

    private ActivityCharacterBinding binding;
    private CharacterAdapter characterAdapter;
    private CharacterRepository repository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityCharacterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        repository = new CharacterRepository();

        binding.btnLoadCharacters.setOnClickListener(v -> {
            String pageText = binding.etPage.getText().toString();
            String pageSizeText = binding.etLimit.getText().toString();
            if (pageText.isEmpty()) {
                binding.etPage.setError("Введите значение");
                return;
            }
            if (pageSizeText.isEmpty()) {
                binding.etLimit.setError("Введите значение");
                return;
            }
            int page = Integer.parseInt(binding.etPage.getText().toString());
            int pageSize = Integer.parseInt(binding.etLimit.getText().toString());
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            binding.etPage.setError(null);
            binding.etLimit.setError(null);
            loadCharacters(page, pageSize);
        });
    }

    private void loadCharacters(int page, int pageSize) {
        if (page < 0) {
            binding.etPage.setError("Только положительное число");
            return;
        }
        if (pageSize < 0) {
            binding.etPage.setError("Только положительное число");
            return;
        }
        repository.getCharacters(page, pageSize, new CharacterRepository.CharacterCallback() {
            @Override
            public void onSuccess(List<Character> characters) {
                characterAdapter = new CharacterAdapter(characters);
                binding.recyclerView.setAdapter(characterAdapter);
            }

            @Override
            public void onError(Throwable t) {
            }
        });
    }
}

