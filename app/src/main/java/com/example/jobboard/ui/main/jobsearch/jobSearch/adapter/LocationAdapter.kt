package com.example.jobboard.ui.main.jobsearch.jobSearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jobboard.data.api.models.LocationApiModel
import com.example.jobboard.databinding.RvItemFilterBinding

class LocationAdapter(
    private val onCheckBoxClick: (LocationApiModel) -> Unit
) : ListAdapter<LocationApiModel, LocationViewHolder>(LocationDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = RvItemFilterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LocationViewHolder(binding, onCheckBoxClick)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class LocationViewHolder(
    private val binding: RvItemFilterBinding,
    private val onCheckBoxClick: (LocationApiModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(location: LocationApiModel) {
        binding.tvCategory.text = location.city

        binding.categoryCheckbox.setOnClickListener {
            onCheckBoxClick.invoke(location)
        }
    }
}

class LocationDiffUtil : DiffUtil.ItemCallback<LocationApiModel>() {

    override fun areItemsTheSame(oldItem: LocationApiModel, newItem: LocationApiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationApiModel, newItem: LocationApiModel): Boolean {
        return oldItem == newItem
    }
}