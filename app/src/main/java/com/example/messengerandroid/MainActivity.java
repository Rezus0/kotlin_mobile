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

import com.example.messengerandroid.fragment.HomeFragment;
import com.example.messengerandroid.fragment.OnboardFragment;
import com.example.messengerandroid.fragment.SignInFragment;
import com.example.messengerandroid.fragment.SignUpFragment;
import com.example.messengerandroid.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            navigateToOnboard();
        }
    }

    public void navigateToOnboard() {
        replaceFragment(new OnboardFragment());
    }

    public void navigateToSignIn() {
        replaceFragment(new SignInFragment());
    }

    public void navigateToSignInWithUser(User user) {
        SignInFragment signInFragment = new SignInFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        signInFragment.setArguments(bundle);

        replaceFragment(signInFragment);
    }

    public void navigateToSignUp() {
        replaceFragment(new SignUpFragment());
    }

    public void navigateToHome() {
        replaceFragment(new HomeFragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}