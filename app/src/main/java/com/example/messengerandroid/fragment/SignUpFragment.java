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
import com.example.messengerandroid.databinding.ActivityOnboardBinding;
import com.example.messengerandroid.databinding.ActivitySignUpBinding;
import com.example.messengerandroid.model.User;

public class SignUpFragment extends Fragment {

    private static final String TAG = "SignUpFragment";

    private ActivitySignUpBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivitySignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onCreate");

        binding.btnRegisterSignUp.setOnClickListener(v -> {
            String username = binding.etUsername.getText().toString().trim();
            String email = binding.etEmailSignUp.getText().toString().trim();
            String password = binding.etPasswordSignUp.getText().toString().trim();

            if (validateInput(username, email, password)) {
                User user = new User(username, email, password);
                ((MainActivity) requireActivity()).navigateToSignInWithUser(user);
            }
        });
    }

    private boolean validateInput(String username, String email, String password) {
        if (TextUtils.isEmpty(username)) {
            binding.etUsername.setError("Введите имя пользователя");
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            binding.etEmailSignUp.setError("Введите email");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmailSignUp.setError("Некорректный формат email");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            binding.etPasswordSignUp.setError("Введите пароль");
            return false;
        }

        if (password.length() < 6) {
            binding.etPasswordSignUp.setError("Пароль должен содержать минимум 6 символов");
            return false;
        }

        return true;
    }
}