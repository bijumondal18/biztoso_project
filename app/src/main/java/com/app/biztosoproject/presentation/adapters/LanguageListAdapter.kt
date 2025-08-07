package com.app.biztosoproject.presentation.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.biztosoproject.R
import com.app.biztosoproject.core.extensions.setSpringBounceClick
import com.app.biztosoproject.data.models.Language
import com.app.biztosoproject.databinding.ItemLanguageBinding
import com.bumptech.glide.Glide

class LanguageListAdapter(
    private val recyclerView: RecyclerView,
    private val onItemClicked: (Int) -> Unit
) :
    ListAdapter<Language, LanguageListAdapter.LanguageViewHolder>(LanguageDiffCallback()) {

    private var selectedPosition: Int? = null

    inner class LanguageViewHolder(val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language, position: Int) {
            binding.tvLanguageName.text = language.languageName
            binding.tvLanguageNameEnglish.text = language.languageNameEnglish

            Glide.with(binding.imageView.context)
                .load(language.image)
                .placeholder(R.drawable.placeholder) // Optional placeholder
                .error(R.drawable.error)             // Optional error image
                .into(binding.imageView)

            val backgroundColor = when (position % 3) {
                0 -> ContextCompat.getColor(
                    binding.root.context,
                    com.app.biztosoproject.R.color.language_one
                ) // Light Orange
                1 -> ContextCompat.getColor(
                    binding.root.context,
                    com.app.biztosoproject.R.color.language_two
                ) // Light Green
                else -> ContextCompat.getColor(
                    binding.root.context,
                    com.app.biztosoproject.R.color.language_three
                ) // Light Blue
            }

            binding.root.setCardBackgroundColor(backgroundColor)

            val iconRes = if (selectedPosition == position) {
                R.drawable.ic_check_circle
            } else {
                R.drawable.ic_outline_circle
            }
            binding.imageCheckCircle.setImageResource(iconRes)

            binding.root.setSpringBounceClick {
                toggleSelection(position)
                onItemClicked(position)
            }
        }
    }

    private fun toggleSelection(position: Int) {
        val previous = selectedPosition
        selectedPosition = if (selectedPosition == position) null else position

        // Update previous icon
        previous?.let {
            val prevHolder =
                recyclerView.findViewHolderForAdapterPosition(it) as? LanguageViewHolder
            prevHolder?.binding?.imageCheckCircle?.setImageResource(R.drawable.ic_outline_circle)
        }

        // Update current icon
        val currHolder =
            recyclerView.findViewHolderForAdapterPosition(position) as? LanguageViewHolder
        val icon = if (selectedPosition == position) R.drawable.ic_check_circle
        else R.drawable.ic_outline_circle
        currHolder?.binding?.imageCheckCircle?.setImageResource(icon)
    }

    fun getSelectedLanguage(): Language? {
        return selectedPosition?.let { getItem(it) }
    }

    override fun submitList(list: List<Language?>?) {
        super.submitList(list)
        // Select the first item by default only if nothing is selected
        if (selectedPosition == null && !list.isNullOrEmpty()) {
            selectedPosition = 0
            notifyItemChanged(0)
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
        if (position != selectedPosition) {
            holder.itemView.animation = AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.item_animatior
            )
        } else {
            holder.itemView.clearAnimation() // Prevent re-animation
        }
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
