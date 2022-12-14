package com.example.jobboard.ui.main.jobsearch.jobSearch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jobboard.R
import com.example.jobboard.data.api.models.JobApiModel
import com.example.jobboard.databinding.RvJobListItemBinding
import com.example.jobboard.utils.ddMMyyyyFormatDate
import com.example.jobboard.utils.fullFormatDate
import com.example.jobboard.utils.src

class JobAdapter(
    private val onItemClick: (JobApiModel) -> Unit,
    private val isFavouriteJobAvailable: Boolean
) : ListAdapter<JobApiModel, JobViewHolder>(JobDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = RvJobListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return JobViewHolder(parent.context, binding, onItemClick, isFavouriteJobAvailable)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class JobViewHolder(
    private val context: Context,
    private val binding: RvJobListItemBinding,
    private val onItemClick: (JobApiModel) -> Unit,
    private val isFavouriteJobAvailable: Boolean
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(job: JobApiModel) {
        val datePosted = fullFormatDate.parse(job.datePosted)
        val datePostedString = ddMMyyyyFormatDate.format(datePosted)
        binding.tvJobCity.text = job.location.city
        binding.tvJobVacation.text = job.name
        binding.tvJobPostedDate.text = datePostedString
        binding.tvJobPostedInfo.text = job.shortDescription
        binding.tvJobEmployment.text = job.employment
        binding.ivCompanyLogo.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.random_logo))

        binding.ivRemoveFromFavourites.isVisible = false
        binding.ivAddToFavourites.isVisible = false

        if(isFavouriteJobAvailable) {
            binding.ivRemoveFromFavourites.isVisible = false
            binding.ivAddToFavourites.isVisible = true
            binding.ivAddToFavourites.src(R.drawable.ic_star_bordered)
            binding.ivRemoveFromFavourites.src(R.drawable.ic_star_filled)

            binding.ivAddToFavourites.setOnClickListener {
                binding.ivAddToFavourites.isVisible = false
                binding.ivRemoveFromFavourites.isVisible = true
            }

            binding.ivRemoveFromFavourites.setOnClickListener {
                binding.ivAddToFavourites.isVisible = true
                binding.ivRemoveFromFavourites.isVisible = false
            }
        }

        binding.root.setOnClickListener {
            onItemClick.invoke(job)
        }
    }
}

class JobDiffUtil : DiffUtil.ItemCallback<JobApiModel>() {

    override fun areItemsTheSame(oldItem: JobApiModel, newItem: JobApiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: JobApiModel, newItem: JobApiModel): Boolean {
        return oldItem == newItem
    }
}