package com.example.messengerandroid.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.messengerandroid.MainActivity
import com.example.messengerandroid.databinding.ActivityOnboardBinding

class OnboardFragment : Fragment() {

    companion object {
        private const val TAG = "OnboardFragment"
    }

    private var binding: ActivityOnboardBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityOnboardBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onCreate")

        binding?.btnNext?.setOnClickListener {
            (requireActivity() as MainActivity).navigateToSignIn()
        }

        binding?.btnNext?.setOnClickListener {
            (requireActivity() as MainActivity).navigateToSignIn()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
