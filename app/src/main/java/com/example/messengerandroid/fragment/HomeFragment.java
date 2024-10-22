package com.example.messengerandroid.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messengerandroid.R;
import com.example.messengerandroid.databinding.ActivityHomeBinding;
import com.example.messengerandroid.databinding.ActivitySignUpBinding;
import com.example.messengerandroid.model.Chat;
import com.example.messengerandroid.model.ChatAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private ActivityHomeBinding binding;

    private ChatAdapter chatAdapter;
    private List<Chat> chatList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onCreate");
        binding.rvChats.setLayoutManager(new LinearLayoutManager(getContext()));
        chatList = getDummyChats();
        chatAdapter = new ChatAdapter(chatList);
        binding.rvChats.setAdapter(chatAdapter);
    }

    private List<Chat> getDummyChats() {
        List<Chat> chats = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            chats.add(new Chat("Иван Иванов", "Привет! Как дела?", "10:30", R.drawable.profile));
            chats.add(new Chat("Мария Смирнова", "Давай встретимся завтра.", "09:15", R.drawable.profile));
            chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        }
        return chats;
    }
}
