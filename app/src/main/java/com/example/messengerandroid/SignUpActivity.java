package com.example.messengerandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.messengerandroid.model.User;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private EditText etUsername, etEmailSignUp, etPasswordSignUp;
    private Button btnRegisterSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_sign_up);

        etUsername = findViewById(R.id.etUsername);
        etEmailSignUp = findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp);
        btnRegisterSignUp = findViewById(R.id.btnRegisterSignUp);

        btnRegisterSignUp.setOnClickListener(view -> {
            String username = etUsername.getText().toString().trim();
            String email = etEmailSignUp.getText().toString().trim();
            String password = etPasswordSignUp.getText().toString().trim();

            if (validateInput(username, email, password)) {
                User user = new User(username, email, password);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("username", username);
                resultIntent.putExtra("email", email);
                resultIntent.putExtra("password", password);

                resultIntent.putExtra("user", user);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private boolean validateInput(String username, String email, String password) {
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Введите имя пользователя");
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            etEmailSignUp.setError("Введите email");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmailSignUp.setError("Некорректный формат email");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            etPasswordSignUp.setError("Введите пароль");
            return false;
        }

        if (password.length() < 6) {
            etPasswordSignUp.setError("Пароль должен содержать минимум 6 символов");
            return false;
        }

        return true;
    }
}
