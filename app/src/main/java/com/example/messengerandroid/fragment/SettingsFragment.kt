package com.example.messengerandroid.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.messengerandroid.MainActivity
import com.example.messengerandroid.R
import com.example.messengerandroid.databinding.FragmentSettingsBinding
import com.example.messengerandroid.service.getNickname
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException

class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val THEME_KEY = "theme_key"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        loadTheme()
        loadNickname()

        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "22.txt")
        binding?.apply {
            if (file.exists()) {
                btnDeleteFile.visibility = View.VISIBLE
            } else {
                btnRestoreFile.visibility = View.VISIBLE
                tvFileStatus.text = getString(R.string.file_status_removed)
            }

            etNickname.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) putNickname(etNickname.text.toString())
            }

            switchTheme.setOnCheckedChangeListener { _, isChecked ->
                applyTheme(isChecked)
            }

            bottomNavigationView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_settings -> true
                    R.id.menu_characters -> {
                        (requireActivity() as MainActivity).navigateToCharacters()
                        true
                    }
                    else -> true
                }
            }

            btnDeleteFile.setOnClickListener {
                if (isFilePresent("22.txt")) {
                    saveBackup(requireContext(), "22.txt")
                    deleteFile("22.txt")
                }
            }

            btnRestoreFile.setOnClickListener {
                restoreFile(requireContext(), "22.txt")
            }
        }
    }

    private fun loadNickname() {
        CoroutineScope(Dispatchers.Main).launch {
            val nickname = getNickname(requireContext())
            binding?.etNickname?.setText(nickname)
        }
    }

    private fun putNickname(nickname: String) {
        CoroutineScope(Dispatchers.IO).launch {
            com.example.messengerandroid.service.saveNickname(requireContext(), nickname)
        }
    }

    private fun loadTheme() {
        val isDarkTheme = sharedPreferences.getBoolean(THEME_KEY, false)
        binding?.switchTheme?.isChecked = isDarkTheme
    }

    private fun applyTheme(isDarkTheme: Boolean) {
        (requireActivity() as MainActivity).changeTheme(isDarkTheme)
    }

    private fun isFilePresent(fileName: String): Boolean {
        val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(directory, fileName)
        return file.exists()
    }

    private fun deleteFile(fileName: String) {
        val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(directory, fileName)
        if (file.exists()) {
            file.delete()
        }
        binding?.apply {
            btnRestoreFile.visibility = View.VISIBLE
            btnDeleteFile.visibility = View.GONE
            tvFileStatus.text = getString(R.string.file_status_removed)
        }
    }

    private fun saveBackup(context: Context, fileName: String) {
        val externalFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName)
        if (!externalFile.exists()) return

        val internalFile = File(context.filesDir, fileName)
        try {
            externalFile.inputStream().use { fis ->
                internalFile.outputStream().use { fos ->
                    fis.copyTo(fos)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun restoreFile(context: Context, fileName: String) {
        val internalFile = File(context.filesDir, fileName)
        if (!internalFile.exists()) return

        val externalFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName)
        try {
            internalFile.inputStream().use { fis ->
                externalFile.outputStream().use { fos ->
                    fis.copyTo(fos)
                }
            }
            binding?.apply {
                btnRestoreFile.visibility = View.GONE
                btnDeleteFile.visibility = View.VISIBLE
                tvFileStatus.text = getString(R.string.file_status_exist)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
