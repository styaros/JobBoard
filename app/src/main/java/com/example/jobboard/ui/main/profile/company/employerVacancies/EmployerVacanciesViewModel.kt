package com.example.jobboard.ui.main.profile.company.employerVacancies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.data.api.models.JobApiModel
import com.example.jobboard.domain.repositories.JobRepository
import com.example.jobboard.domain.repositories.SharedPrefsRepository
import com.example.jobboard.domain.repositories.UserInfoRepository
import kotlinx.coroutines.launch

class EmployerVacanciesViewModel(
    private val userRepository: UserInfoRepository,
    private val jobRepository: JobRepository,
    private val sharedPrefsRepository: SharedPrefsRepository
) : ViewModel() {

    private val _vacancyListLiveData = MutableLiveData<List<JobApiModel>>(listOf())
    val vacancyListLiveData: MutableLiveData<List<JobApiModel>>
        get() = _vacancyListLiveData

    fun getJobs() {
        viewModelScope.launch {
            _vacancyListLiveData.value = userRepository.getEmployerInfo(sharedPrefsRepository.getUserId()!!).jobs.jobs
        }
    }

    fun deleteVacancy(jobId: String) {
        viewModelScope.launch {
            jobRepository.deleteJob(jobId, sharedPrefsRepository.getUserId()!!)
        }
    }
}