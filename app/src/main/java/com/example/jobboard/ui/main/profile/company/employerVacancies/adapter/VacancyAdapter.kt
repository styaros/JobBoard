package com.example.jobboard.ui.main.profile.company.employerVacancies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jobboard.R
import com.example.jobboard.data.api.models.JobApiModel
import com.example.jobboard.databinding.RvItemEmployerVacancyBinding
import com.example.jobboard.utils.ddMMyyyyFormatDate
import com.example.jobboard.utils.fullFormatDate

class VacancyAdapter(
    private val onEditClick: (JobApiModel) -> Unit,
    private val onDeleteClick: (JobApiModel) -> Unit
) : ListAdapter<JobApiModel, VacancyViewHolder>(VacancyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val binding = RvItemEmployerVacancyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VacancyViewHolder(parent.context, binding, onEditClick, onDeleteClick)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class VacancyViewHolder(
    private val context: Context,
    private val binding: RvItemEmployerVacancyBinding,
    private val onEditClick: (JobApiModel) -> Unit,
    private val onDeleteClick: (JobApiModel) -> Unit
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

        binding.btnEdit.setOnClickListener {
            onEditClick.invoke(job)
        }

        binding.btnDelete.setOnClickListener {
            onDeleteClick.invoke(job)
        }
    }
}

class VacancyDiffUtil : DiffUtil.ItemCallback<JobApiModel>() {

    override fun areItemsTheSame(oldItem: JobApiModel, newItem: JobApiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: JobApiModel, newItem: JobApiModel): Boolean {
        return oldItem == newItem
    }
}