package com.example.jobboard.ui.main.jobsearch.jobSearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jobboard.databinding.RvItemFilterBinding

class ExperienceAdapter(
    private val onCheckBoxClick: (Int) -> Unit
) : ListAdapter<Int, ExperienceViewHolder>(ExperienceDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceViewHolder {
        val binding = RvItemFilterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExperienceViewHolder(binding, onCheckBoxClick)
    }

    override fun onBindViewHolder(holder: ExperienceViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class ExperienceViewHolder(
    private val binding: RvItemFilterBinding,
    private val onCheckBoxClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(experience: Int) {
        binding.tvCategory.text = experience.toString()

        binding.categoryCheckbox.setOnClickListener {
            onCheckBoxClick.invoke(experience)
        }
    }
}

class ExperienceDiffUtil : DiffUtil.ItemCallback<Int>() {

    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}