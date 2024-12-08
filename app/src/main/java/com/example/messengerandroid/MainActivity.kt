package com.example.messengerandroid

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.messengerandroid.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val THEME_KEY = "theme_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val isDarkTheme = sharedPreferences.getBoolean(THEME_KEY, false)
        setAppTheme(isDarkTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        navHostFragment?.let {
            navController = it.navController
        }

        if (savedInstanceState == null) {
            navigateToOnboard()
        }
    }

    fun navigateToOnboard() {
        navController.navigate(R.id.onboardFragment)
    }

    fun navigateToSignIn() {
        navController.navigate(R.id.signInFragment)
    }

    fun navigateToSignInWithUser(user: User) {
        val bundle = Bundle().apply {
            putSerializable("user", user)
        }
        navController.navigate(R.id.action_signUpFragment_to_signInFragment, bundle)
    }

    fun navigateToSignUp() {
        navController.navigate(R.id.signUpFragment)
    }

    fun navigateToCharacters() {
        navController.navigate(R.id.characterFragment)
    }

    fun navigateToSettings() {
        navController.navigate(R.id.settingsFragment)
    }

    fun changeTheme(isDarkTheme: Boolean) {
        sharedPreferences.edit().putBoolean(THEME_KEY, isDarkTheme).apply()
        setAppTheme(isDarkTheme)
        recreate()
    }

    private fun setAppTheme(isDarkTheme: Boolean) {
        setTheme(if (isDarkTheme) R.style.Theme_MessengerApp_Dark else R.style.Theme_MessengerApp_Light)
    }
}

