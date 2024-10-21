package com.example.messengerandroid.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.messengerandroid.MainActivity;
import com.example.messengerandroid.R;
import com.example.messengerandroid.model.User;

public class SignUpFragment extends Fragment {

    private static final String TAG = "SignUpFragment";
    private EditText etUsername, etEmailSignUp, etPasswordSignUp;
    private Button btnRegisterSignUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onCreate");

        etUsername = view.findViewById(R.id.etUsername);
        etEmailSignUp = view.findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = view.findViewById(R.id.etPasswordSignUp);
        btnRegisterSignUp = view.findViewById(R.id.btnRegisterSignUp);

        btnRegisterSignUp.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String email = etEmailSignUp.getText().toString().trim();
            String password = etPasswordSignUp.getText().toString().trim();

            if (validateInput(username, email, password)) {
                User user = new User(username, email, password);
                ((MainActivity) requireActivity()).navigateToSignInWithUser(user);
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