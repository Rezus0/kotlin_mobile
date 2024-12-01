package com.example.messengerandroid.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messengerandroid.databinding.ItemCharacterBinding;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private final List<Character> characterList;

    public CharacterAdapter(List<Character> characters) {
        this.characterList = characters;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new CharacterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.bind(character);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {

        private final ItemCharacterBinding binding;

        public CharacterViewHolder(ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Character character) {
            binding.tvName.setText(character.getName() == null || character.getName().isEmpty() ? "Unknown" : character.getName());
            binding.tvCulture.setText(character.getCulture() == null || character.getCulture().isEmpty() ? "Unknown" : character.getCulture());
            binding.tvBorn.setText(character.getBorn() == null || character.getBorn().isEmpty() ? "Unknown" : character.getBorn());
            binding.tvTitles.setText(character.getTitles().isEmpty() ? "Unknown" : String.join(", ", character.getTitles()));
            binding.tvAliases.setText(character.getAliases().isEmpty() ? "Unknown" : String.join(", ", character.getAliases()));
            binding.tvPlayedBy.setText(character.getPlayedBy().isEmpty() ? "Unknown" : String.join(", ", character.getPlayedBy()));
        }
    }
}


