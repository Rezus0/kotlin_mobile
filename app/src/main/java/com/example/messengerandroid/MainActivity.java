package com.example.messengerandroid;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.example.messengerandroid.fragment.SignUpFragmentDirections;
import com.example.messengerandroid.model.User;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private SharedPreferences sharedPreferences;
    private static final String THEME_KEY = "theme_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE);
        boolean isDarkTheme = sharedPreferences.getBoolean(THEME_KEY, false);
        setAppTheme(isDarkTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        if (savedInstanceState == null) {
            navigateToOnboard();
        }
    }

    public void navigateToOnboard() {
        navController.navigate(R.id.onboardFragment);
    }

    public void navigateToSignIn() {
        navController.navigate(R.id.signInFragment);
    }

    public void navigateToSignInWithUser(User user) {
        NavDirections action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment(user);
        navController.navigate(action);
    }

    public void navigateToSignUp() {
        navController.navigate(R.id.signUpFragment);
    }

    public void navigateToCharacters() {
        navController.navigate(R.id.characterFragment);
    }

    public void navigateToSettings() {
        navController.navigate(R.id.settingsFragment);
    }

    public void changeTheme(boolean isDarkTheme) {
        sharedPreferences.edit().putBoolean(THEME_KEY, isDarkTheme).apply();
        setAppTheme(isDarkTheme);
        recreate();
    }

    private void setAppTheme(boolean isDarkTheme) {
        if (isDarkTheme) {
            setTheme(R.style.Theme_MessengerApp_Dark);
        } else {
            setTheme(R.style.Theme_MessengerApp_Light);
        }
    }
}