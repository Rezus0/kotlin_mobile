package com.example.messengerandroid.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.messengerandroid.MainActivity;
import com.example.messengerandroid.R;


public class OnboardFragment extends Fragment {

    private static final String TAG = "OnboardFragment";

    private Button btnNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_onboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onCreate");
        EdgeToEdge.enable(requireActivity());
        btnNext = view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(v -> ((MainActivity) requireActivity()).navigateToSignIn());
    }
}
