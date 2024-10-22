package com.example.messengerandroid;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.example.messengerandroid.fragment.HomeFragment;
import com.example.messengerandroid.fragment.OnboardFragment;
import com.example.messengerandroid.fragment.SignInFragment;
import com.example.messengerandroid.fragment.SignInFragmentDirections;
import com.example.messengerandroid.fragment.SignUpFragment;
import com.example.messengerandroid.fragment.SignUpFragmentDirections;
import com.example.messengerandroid.model.User;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    public void navigateToHome() {
        navController.navigate(R.id.homeFragment);
    }
}