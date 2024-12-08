package com.example.messengerandroid.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.messengerandroid.databinding.ItemCharacterBinding

class CharacterAdapter(
    private val characterList: List<Character>
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int = characterList.size

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.apply {
                tvName.text = character.name.takeIf { !it.isNullOrEmpty() } ?: "Unknown"
                tvCulture.text = character.culture.takeIf { !it.isNullOrEmpty() } ?: "Unknown"
                tvBorn.text = character.born.takeIf { !it.isNullOrEmpty() } ?: "Unknown"
                tvTitles.text = character.titles?.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: "Unknown"
                tvAliases.text = character.aliases?.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: "Unknown"
                tvPlayedBy.text = character.playedBy?.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: "Unknown"
            }
        }
    }
}
