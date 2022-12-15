package com.example.jobboard.ui.main.profile.company.employerVacancies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.data.api.models.JobApiModel
import com.example.jobboard.data.api.models.VacancyApiModel
import com.example.jobboard.domain.repositories.JobRepository
import com.example.jobboard.domain.repositories.SharedPrefsRepository
import com.example.jobboard.domain.repositories.UserInfoRepository
import kotlinx.coroutines.launch

class EmployerVacanciesViewModel(
    private val userRepository: UserInfoRepository,
    private val jobRepository: JobRepository,
    private val sharedPrefsRepository: SharedPrefsRepository
) : ViewModel() {

    private val _vacancyListLiveData = MutableLiveData<List<VacancyApiModel>>(listOf())
    val vacancyListLiveData: MutableLiveData<List<VacancyApiModel>>
        get() = _vacancyListLiveData

    fun getJobs() {
        viewModelScope.launch {
            _vacancyListLiveData.value = userRepository.getEmployerInfo(sharedPrefsRepository.getUserId()!!).jobs ?: listOf()
        }
    }

    fun deleteVacancy(jobId: String) {
        viewModelScope.launch {
            val list = vacancyListLiveData.value?.toMutableList()
            list?.removeAll { jobModel ->
                jobModel.id == jobId
            }
            _vacancyListLiveData.value = list!!
            jobRepository.deleteJob(jobId, sharedPrefsRepository.getUserId()!!)
        }
    }
}