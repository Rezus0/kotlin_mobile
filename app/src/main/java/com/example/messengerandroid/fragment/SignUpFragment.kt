package com.example.messengerandroid.fragment

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.messengerandroid.MainActivity
import com.example.messengerandroid.R
import com.example.messengerandroid.databinding.ActivitySignUpBinding
import com.example.messengerandroid.model.User
import com.example.messengerandroid.service.saveNickname
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    private var binding: ActivitySignUpBinding? = null

    companion object {
        private const val TAG = "SignUpFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivitySignUpBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onCreate")

        binding?.btnRegisterSignUp?.setOnClickListener {
            val username = binding?.etUsername?.text.toString().trim()
            val email = binding?.etEmailSignUp?.text.toString().trim()
            val password = binding?.etPasswordSignUp?.text.toString().trim()

            if (validateInput(username, email, password)) {
                val user = User(username, email, password)
                CoroutineScope(Dispatchers.IO).launch {
                    saveNickname(requireContext(), username)
                }
                (requireActivity() as MainActivity).navigateToSignInWithUser(user)
            }
        }
    }

    private fun validateInput(username: String, email: String, password: String): Boolean {
        var isValid = true

        binding?.apply {
            if (username.isEmpty()) {
                etUsername.error = getString(R.string.enter_username)
                isValid = false
            }

            if (email.isEmpty()) {
                etEmailSignUp.error = getString(R.string.enter_email)
                isValid = false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmailSignUp.error = getString(R.string.invalid_email_format)
                isValid = false
            }

            if (password.isEmpty()) {
                etPasswordSignUp.error = getString(R.string.enter_password)
                isValid = false
            } else if (password.length < 6) {
                etPasswordSignUp.error = getString(R.string.password_too_short)
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
