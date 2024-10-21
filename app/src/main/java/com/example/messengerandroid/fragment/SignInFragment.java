package com.example.messengerandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.messengerandroid.MainActivity;
import com.example.messengerandroid.R;
import com.example.messengerandroid.model.User;

import java.util.HashMap;

public class SignInFragment extends Fragment {

    private static final String TAG = "SignInFragment";

    private EditText etEmail, etPassword;
    private Button btnSignIn, btnRegister;

    private HashMap<String, String> users = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onCreate");

        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        btnSignIn = view.findViewById(R.id.btnSignIn);
        btnRegister = view.findViewById(R.id.btnRegister);

        if (getArguments() != null) {
            User user = (User) getArguments().getSerializable("user");
            if (user != null) {
                users.put(user.getEmail(), user.getPassword());
                etEmail.setText(user.getEmail());
                Toast.makeText(getActivity(), "Регистрация успешна!\nИмя: " + user.getUsername()
                        + "\nEmail: " + user.getEmail(), Toast.LENGTH_SHORT).show();
            }
        }

        btnSignIn.setOnClickListener(v -> {
            String emailInput = etEmail.getText().toString().trim();
            String passwordInput = etPassword.getText().toString().trim();

            if (validateInput(emailInput, passwordInput)) {
                if (passwordInput.equals(users.get(emailInput))) {
                    ((MainActivity) requireActivity()).navigateToHome();
                } else {
                    Toast.makeText(getActivity(), "Неверный email или пароль", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(v -> ((MainActivity) requireActivity()).navigateToSignUp());
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
}
