package com.example.jobboard.ui.main.profile.company.addVacancy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.data.api.models.CategoryApiModel
import com.example.jobboard.data.api.models.JobApiCreateModel
import com.example.jobboard.domain.repositories.CategoryRepository
import com.example.jobboard.domain.repositories.JobRepository
import com.example.jobboard.domain.repositories.SharedPrefsRepository
import kotlinx.coroutines.launch

class AddVacancyViewModel(
    private val sharedPrefsRepository: SharedPrefsRepository,
    private val categoryRepository: CategoryRepository,
    private val jobRepository: JobRepository
) : ViewModel() {

    private val _categoryListLiveData = MutableLiveData<List<CategoryApiModel>>()
    val categoryListLiveData: LiveData<List<CategoryApiModel>>
        get() = _categoryListLiveData

    private val _categoryLiveData = MutableLiveData<CategoryApiModel>()

    private val _ping = MutableLiveData(false)
    val ping: MutableLiveData<Boolean>
        get() = _ping

    fun getCategories() {
        viewModelScope.launch {
            _categoryListLiveData.value = categoryRepository.getAllCategories()
        }
    }

    fun addVacancy(
        name: String,
        description: String,
        location: String,
        salaryStart: Int,
        salaryEnd: Int,
        experience: Int
    ) {
        viewModelScope.launch {
            val userId = sharedPrefsRepository.getUserId()!!
            val job = JobApiCreateModel(
                name,
                description,
                location,
                8,
                salaryStart,
                salaryEnd,
                experience,
                "Full time",
                _categoryLiveData.value!!.id
            )
            jobRepository.createJob(userId, job)
            _ping.value = true
        }
    }

    fun refreshCategory(category: CategoryApiModel) {
        viewModelScope.launch {
            _categoryLiveData.value = category
        }
    }
}