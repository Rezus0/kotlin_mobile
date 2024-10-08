package com.example.messengerandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class OnboardActivity extends AppCompatActivity {

    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onboard);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(view -> {
            Intent intent = new Intent(OnboardActivity.this, SignInActivity.class);
            startActivity(intent);
        });
    }
}