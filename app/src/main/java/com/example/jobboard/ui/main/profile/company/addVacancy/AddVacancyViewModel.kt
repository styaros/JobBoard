package com.example.jobboard.ui.main.profile.company.addVacancy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.data.api.models.CategoryApiModel
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

    fun getCategories() {
        viewModelScope.launch {
            _categoryListLiveData.value = categoryRepository.getAllCategories()
        }
    }
}