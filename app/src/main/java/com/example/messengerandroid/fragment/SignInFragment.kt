package com.example.messengerandroid.fragment

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.messengerandroid.MainActivity
import com.example.messengerandroid.R
import com.example.messengerandroid.databinding.ActivitySignInBinding

class SignInFragment : Fragment() {

    private var binding: ActivitySignInBinding? = null

    private val users = hashMapOf<String, String>()

    companion object {
        private const val TAG = "SignInFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivitySignInBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onCreate")

        arguments?.let { bundle ->
            val user = SignInFragmentArgs.fromBundle(bundle).user
            user?.let {
                users[it.email] = it.password
                binding?.etEmail?.setText(it.email)
                Toast.makeText(
                    activity,
                    getString(R.string.registration_success, it.username, it.email),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding?.apply {
            btnSignIn.setOnClickListener {
                val emailInput = etEmail.text.toString().trim()
                val passwordInput = etPassword.text.toString().trim()

                if (validateInput(emailInput, passwordInput)) {
                    if (users[emailInput] == passwordInput) {
                        (requireActivity() as MainActivity).navigateToCharacters()
                    } else {
                        Toast.makeText(
                            activity,
                            getString(R.string.invalid_email_password),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            btnRegister.setOnClickListener {
                (requireActivity() as MainActivity).navigateToSignUp()
            }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        var isValid = true

        binding?.apply {
            if (email.isEmpty()) {
                etEmail.error = getString(R.string.enter_email)
                isValid = false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = getString(R.string.invalid_email_format)
                isValid = false
            }

            if (password.isEmpty()) {
                etPassword.error = getString(R.string.enter_password)
                isValid = false
            } else if (password.length < 6) {
                etPassword.error = getString(R.string.password_too_short)
                isValid = false
            }
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
