package com.example.jobboard.ui.main.profile.company.addVacancy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jobboard.data.api.models.CategoryApiModel
import com.example.jobboard.databinding.RvItemFilterBinding
import com.example.jobboard.databinding.RvItemJustTextBinding

class CategoryAdapter(
    private val onCheckBoxClick: (CategoryApiModel) -> Unit
) : ListAdapter<CategoryApiModel, CategoryViewHolder>(CategoryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = RvItemJustTextBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding, onCheckBoxClick)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class CategoryViewHolder(
    private val binding: RvItemJustTextBinding,
    private val onCheckBoxClick: (CategoryApiModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: CategoryApiModel) {
        binding.tvItem.text = category.name

        binding.root.setOnClickListener {
            onCheckBoxClick.invoke(category)
        }
    }
}

class CategoryDiffUtil : DiffUtil.ItemCallback<CategoryApiModel>() {

    override fun areItemsTheSame(oldItem: CategoryApiModel, newItem: CategoryApiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryApiModel, newItem: CategoryApiModel): Boolean {
        return oldItem == newItem
    }
}