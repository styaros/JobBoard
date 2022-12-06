package com.example.jobboard.ui.main.jobsearch.jobsearch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jobboard.R
import com.example.jobboard.data.api.models.CategoryApiModel
import com.example.jobboard.data.api.models.JobApiModel
import com.example.jobboard.databinding.RvItemFilterBinding
import com.example.jobboard.databinding.RvJobListItemBinding
import com.example.jobboard.utils.ddMMyyyyFormatDate
import com.example.jobboard.utils.fullFormatDate

class CategoryAdapter : ListAdapter<CategoryApiModel, CategoryViewHolder>(CategoryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = RvItemFilterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class CategoryViewHolder(
    private val context: Context,
    private val binding: RvItemFilterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: CategoryApiModel) {

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