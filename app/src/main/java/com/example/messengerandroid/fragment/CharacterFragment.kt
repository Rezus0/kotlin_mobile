package com.example.messengerandroid.fragment

import android.app.Activity
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.messengerandroid.MainActivity
import com.example.messengerandroid.R
import com.example.messengerandroid.databinding.ActivityCharacterBinding
import com.example.messengerandroid.model.Character
import com.example.messengerandroid.model.CharacterAdapter
import com.example.messengerandroid.service.CharacterRepository
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class CharacterFragment : Fragment() {

    private var binding: ActivityCharacterBinding? = null
    private var characterAdapter: CharacterAdapter? = null
    private lateinit var repository: CharacterRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityCharacterBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            repository = CharacterRepository()

            btnLoadCharacters.setOnClickListener { v ->
                val pageText = etPage.text.toString()
                val pageSizeText = etLimit.text.toString()
                if (pageText.isEmpty()) {
                    etPage.error = "Введите значение"
                    return@setOnClickListener
                }
                if (pageSizeText.isEmpty()) {
                    etLimit.error = "Введите значение"
                    return@setOnClickListener
                }
                val page = pageText.toIntOrNull() ?: 0
                val pageSize = pageSizeText.toIntOrNull() ?: 0
                val imm = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                etPage.error = null
                etLimit.error = null
                loadCharacters(page, pageSize)
            }

            bottomNavigationView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_settings -> {
                        (requireActivity() as MainActivity).navigateToSettings()
                        true
                    }
                    R.id.menu_characters -> true
                    else -> true
                }
            }
        }
    }

    private fun loadCharacters(page: Int, pageSize: Int) {
        if (page < 0) {
            binding?.etPage?.error = "Только положительное число"
            return
        }
        if (pageSize < 0) {
            binding?.etPage?.error = "Только положительное число"
            return
        }

        repository.getCharacters(page, pageSize, object : CharacterRepository.CharacterCallback {
            override fun onSuccess(characters: List<Character>) {
                characterAdapter = CharacterAdapter(characters)
                saveCharactersToFile(characters)
                binding?.recyclerView?.adapter = characterAdapter
            }

            override fun onError(t: Throwable) {
                Toast.makeText(
                    requireActivity(),
                    "Не удалось получить список персонажей из-за непредвиденной ошибки",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun saveCharactersToFile(characters: List<Character>) {
        val fileName = "22.txt"
        val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val file = File(directory, fileName)
        try {
            FileOutputStream(file).use { fos ->
                val data = characters.joinToString(separator = "\n") { it.toString() }
                fos.write(data.toByteArray())
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
