package com.example.messengerandroid.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.messengerandroid.MainActivity;
import com.example.messengerandroid.R;
import com.example.messengerandroid.databinding.FragmentSettingsBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private SharedPreferences sharedPreferences;

    private static final String THEME_KEY = "theme_key";
    private static final String NICKNAME_KEY = "nickname_key";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = requireContext().getSharedPreferences("app_prefs", requireContext().MODE_PRIVATE);
        loadTheme();
        loadNickname();

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "22.txt");
        if (file.exists()) {
            binding.btnDeleteFile.setVisibility(View.VISIBLE);
        } else {
            binding.btnRestoreFile.setVisibility(View.VISIBLE);
            binding.tvFileStatus.setText(getString(R.string.file_status_removed));
        }


        binding.etNickname.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) saveNickname(binding.etNickname.getText().toString());
        });

        binding.switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> applyTheme(isChecked));

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_settings) {
                return true;
            } else if (item.getItemId() == R.id.menu_characters) {
                ((MainActivity) requireActivity()).navigateToCharacters();
            }
            return true;
        });

        binding.btnDeleteFile.setOnClickListener(v -> {
            if (isFilePresent("22.txt")) {
                saveBackup(getContext(), "22.txt");
                deleteFile("22.txt");
            }
        });

        binding.btnRestoreFile.setOnClickListener(v -> {
            restoreFile(requireContext(), "22.txt");
        });

    }

    private void loadNickname() {
        String nickname = sharedPreferences.getString(NICKNAME_KEY, "");
        if (!nickname.isEmpty()) {
            binding.etNickname.setText(nickname);
        }
    }

    private void saveNickname(String nickname) {
        sharedPreferences.edit().putString(NICKNAME_KEY, nickname).apply();
    }

    private void loadTheme() {
        boolean isDarkTheme = sharedPreferences.getBoolean(THEME_KEY, false);
        binding.switchTheme.setChecked(isDarkTheme);
    }

    private void applyTheme(boolean isDarkTheme) {
        ((MainActivity) requireActivity()).changeTheme(isDarkTheme);
    }

    private boolean isFilePresent(String fileName) {
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(directory, fileName);
        return file.exists();
    }

    private void deleteFile(String fileName) {
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(directory, fileName);
        if (file.exists()) {
            file.delete();
        }
        binding.btnRestoreFile.setVisibility(View.VISIBLE);
        binding.btnDeleteFile.setVisibility(View.GONE);
        binding.tvFileStatus.setText(getString(R.string.file_status_removed));
    }

    private void saveBackup(Context context, String fileName) {
        File externalFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
        if (!externalFile.exists()) return;

        File internalFile = new File(context.getFilesDir(), fileName);
        try (FileInputStream fis = new FileInputStream(externalFile);
             FileOutputStream fos = new FileOutputStream(internalFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void restoreFile(Context context, String fileName) {
        File internalFile = new File(context.getFilesDir(), fileName);
        if (!internalFile.exists()) return;

        File externalFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
        try (FileInputStream fis = new FileInputStream(internalFile);
             FileOutputStream fos = new FileOutputStream(externalFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            binding.btnRestoreFile.setVisibility(View.GONE);
            binding.btnDeleteFile.setVisibility(View.VISIBLE);
            binding.tvFileStatus.setText(getString(R.string.file_status_exist));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}