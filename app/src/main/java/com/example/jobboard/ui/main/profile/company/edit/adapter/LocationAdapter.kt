package com.example.jobboard.ui.main.profile.company.edit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jobboard.data.api.models.LocationApiModel
import com.example.jobboard.databinding.RvItemFilterBinding
import com.example.jobboard.databinding.RvItemJustTextBinding

class LocationAdapter(
    private val onItemClick: (LocationApiModel) -> Unit
) : ListAdapter<LocationApiModel, LocationViewHolder>(LocationDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = RvItemJustTextBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LocationViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class LocationViewHolder(
    private val binding: RvItemJustTextBinding,
    private val onItemClick: (LocationApiModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(location: LocationApiModel) {
        binding.tvItem.text = location.city

        binding.root.setOnClickListener {
            onItemClick.invoke(location)
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