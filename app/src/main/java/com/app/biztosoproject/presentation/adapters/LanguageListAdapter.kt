package com.app.biztosoproject.presentation.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.biztosoproject.data.models.Language
import com.app.biztosoproject.databinding.ItemLanguageBinding

class LanguageListAdapter :
    ListAdapter<Language, LanguageListAdapter.LanguageViewHolder>(LanguageDiffCallback()) {

    inner class LanguageViewHolder(private val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language, position: Int) {
            binding.tvLanguageName.text = language.languageName
            binding.tvLanguageNameEnglish.text = language.languageNameEnglish

            val backgroundColor = when (position % 3) {
                0 -> ContextCompat.getColor(binding.root.context, com.app.biztosoproject.R.color.language_one) // Light Orange
                1 -> ContextCompat.getColor(binding.root.context, com.app.biztosoproject.R.color.language_two) // Light Green
                else -> ContextCompat.getColor(binding.root.context, com.app.biztosoproject.R.color.language_three) // Light Blue
            }

            binding.root.setCardBackgroundColor(backgroundColor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding =
            ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: LanguageViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position), position)
    }

}

class LanguageDiffCallback : DiffUtil.ItemCallback<Language>() {
    override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean {
        return oldItem == newItem
    }
}
