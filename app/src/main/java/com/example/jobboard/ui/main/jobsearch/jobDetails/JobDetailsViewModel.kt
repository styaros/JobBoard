package com.example.jobboard.ui.main.jobsearch.jobDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.data.api.models.JobDetailsApiModel
import com.example.jobboard.domain.repositories.JobRepository
import kotlinx.coroutines.launch

class JobDetailsViewModel(
    private val jobRepository: JobRepository
) : ViewModel() {

    private val _jobDetails = MutableLiveData<JobDetailsApiModel>()
    val jobDetails: LiveData<JobDetailsApiModel>
        get() = _jobDetails

    fun getJobById(id: String) {
        viewModelScope.launch {
            _jobDetails.value = jobRepository.getJobById(id)
        }
    }
}