package com.example.messengerandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.messengerandroid.model.User;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";

    private EditText etEmail, etPassword;
    private Button btnSignIn, btnRegister;

    private HashMap<String, String> users = new HashMap<>();

    private ActivityResultLauncher<Intent> signUpActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_sign_in);


        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnRegister = findViewById(R.id.btnRegister);

        signUpActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String username = data.getStringExtra("username");
                        String email = data.getStringExtra("email");
                        String password = data.getStringExtra("password");

                        users.put(email, password);

                        User user = (User) data.getSerializableExtra("user");

                        if (user != null) {
                            Toast.makeText(SignInActivity.this, "Регистрация успешна!\nИмя: " + user.getUsername()
                                    + "\nEmail: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                            etEmail.setText(user.getEmail());
                        } else {
                            Toast.makeText(SignInActivity.this, "Регистрация успешна!\nИмя: " +
                                    username + "\nEmail: " + email, Toast.LENGTH_SHORT).show();
                            etEmail.setText(email);
                        }
                    }
                }
        );


        btnSignIn.setOnClickListener(view -> {
            String emailInput = etEmail.getText().toString().trim();
            String passwordInput = etPassword.getText().toString().trim();

            if (validateInput(emailInput, passwordInput)) {
                if (passwordInput.equals(users.get(emailInput))) {
                    Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignInActivity.this, "Неверный email или пароль", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            signUpActivityResultLauncher.launch(intent);
        });
    }

    private boolean validateInput(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Введите email");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Некорректный формат email");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Введите пароль");
            return false;
        }

        if (password.length() < 6) {
            etPassword.setError("Пароль должен содержать минимум 6 символов");
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            String username = data.getStringExtra("username");
            String email = data.getStringExtra("email");
            User user = (User) data.getSerializableExtra("user");
            if (user != null) {
                Toast.makeText(this, "Регистрация успешна!\nИмя: " + user.getUsername()
                        + "\nEmail: " + user.getEmail(), Toast.LENGTH_LONG).show();
                // Установка данных в поля ввода, если требуется
                etEmail.setText(user.getEmail());
            } else {
                Toast.makeText(this, "Регистрация успешна!\nИмя: " +
                        username + "\nEmail: " + email, Toast.LENGTH_LONG).show();
                // Установка данных в поля ввода, если требуется
                etEmail.setText(email);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}