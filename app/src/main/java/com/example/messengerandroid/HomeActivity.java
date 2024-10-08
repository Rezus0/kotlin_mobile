package com.example.messengerandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messengerandroid.model.Chat;
import com.example.messengerandroid.model.ChatAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvChats;
    private ChatAdapter chatAdapter;
    private List<Chat> chatList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rvChats = findViewById(R.id.rvChats);
        rvChats.setLayoutManager(new LinearLayoutManager(this));
        chatList = getDummyChats();
        chatAdapter = new ChatAdapter(chatList);
        rvChats.setAdapter(chatAdapter);
    }

    private List<Chat> getDummyChats() {
        List<Chat> chats = new ArrayList<>();
        chats.add(new Chat("Иван Иванов", "Привет! Как дела?", "10:30", R.drawable.profile));
        chats.add(new Chat("Мария Смирнова", "Давай встретимся завтра.", "09:15", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        chats.add(new Chat("Петр Петров", "Отправил файлы.", "Вчера", R.drawable.profile));
        return chats;
    }
}