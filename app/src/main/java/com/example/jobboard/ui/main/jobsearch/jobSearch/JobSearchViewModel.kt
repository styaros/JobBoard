package com.example.jobboard.ui.main.jobsearch.jobSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.data.api.CategoryApi
import com.example.jobboard.data.api.models.CategoryApiModel
import com.example.jobboard.data.api.models.JobApiModel
import com.example.jobboard.data.api.models.LocationApiModel
import com.example.jobboard.domain.models.api.FilterQuery
import com.example.jobboard.domain.models.api.Filters
import com.example.jobboard.domain.models.api.Sort
import com.example.jobboard.domain.repositories.CategoryRepository
import com.example.jobboard.domain.repositories.JobRepository
import com.example.jobboard.domain.repositories.LocationRepository
import kotlinx.coroutines.launch

class JobSearchViewModel(
    private val jobRepository: JobRepository,
    private val categoryRepository: CategoryRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _jobList = MutableLiveData<List<JobApiModel>>()
    val jobList: LiveData<List<JobApiModel>>
        get() = _jobList

    private val _filterQuery = MutableLiveData(FilterQuery())
    val filterQuery: LiveData<FilterQuery>
        get() = _filterQuery

    private val _categoryList = MutableLiveData<List<CategoryApiModel>>()
    val categoryList: LiveData<List<CategoryApiModel>>
        get() = _categoryList

    private val _locationList = MutableLiveData<List<LocationApiModel>>()
    val locationList: LiveData<List<LocationApiModel>>
        get() = _locationList

    fun getJobsByFilter() {
        viewModelScope.launch {
            _jobList.value = jobRepository.getJobsByFilters(filterQuery.value!!)
        }
    }

    fun getFilters() {
        viewModelScope.launch {
            _categoryList.value = categoryRepository.getAllCategories()
        }
        viewModelScope.launch {
            _locationList.value = locationRepository.getAllLocations()
        }
    }

    fun setFilters(
        categoryIdsList: List<String>,
        locationIdsList: List<String>,
        experienceList: List<Int>
    ) {
        _filterQuery.value = _filterQuery.value?.copy(
            filters = _filterQuery.value?.filters?.copy(
                categoryIds = categoryIdsList,
                locationIds = locationIdsList,
                experiences = experienceList
            ) ?: Filters(
                categoryIds = null,
                locationIds = null,
                experiences = null
            )
        )
    }

    fun searchByKeyword(keyword: String?) {
        _filterQuery.value = _filterQuery.value?.copy(
            filters = _filterQuery.value?.filters?.copy(
                keyWord = keyword
            ) ?: Filters(keyWord = keyword)
        )
    }

    fun setSort(code: SortCode) {
        when (code) {
            SortCode.ByDefault -> {
                _filterQuery.value = filterQuery.value?.copy(
                    sort = Sort(
                        sortByName = false,
                        sortBySalary = false,
                        sortByExperience = false,
                        isAscending = false
                    )
                )
            }
            SortCode.ByExperienceAscending -> {
                _filterQuery.value = filterQuery.value?.copy(
                    sort = Sort(
                        sortByName = false,
                        sortBySalary = false,
                        sortByExperience = true,
                        isAscending = true
                    )
                )
            }
            SortCode.ByExperienceDescending -> {
                _filterQuery.value = filterQuery.value?.copy(
                    sort = Sort(
                        sortByName = false,
                        sortBySalary = false,
                        sortByExperience = true,
                        isAscending = false
                    )
                )
            }
            SortCode.BySalaryAscending -> {
                _filterQuery.value = filterQuery.value?.copy(
                    sort = Sort(
                        sortByName = false,
                        sortBySalary = true,
                        sortByExperience = false,
                        isAscending = true
                    )
                )
            }
            SortCode.BySalaryDescending -> {
                _filterQuery.value = filterQuery.value?.copy(
                    sort = Sort(
                        sortByName = false,
                        sortBySalary = true,
                        sortByExperience = false,
                        isAscending = false
                    )
                )
            }
            SortCode.ByTitleAscending -> {
                _filterQuery.value = filterQuery.value?.copy(
                    sort = Sort(
                        sortByName = true,
                        sortBySalary = false,
                        sortByExperience = false,
                        isAscending = true
                    )
                )
            }
            SortCode.ByTitleDescending -> {
                _filterQuery.value = _filterQuery.value?.copy(
                    sort = Sort(
                        sortByName = true,
                        sortBySalary = false,
                        sortByExperience = false,
                        isAscending = false
                    )
                )
            }
        }
    }
}

sealed class SortCode {
    object ByDefault : SortCode()
    object ByTitleAscending : SortCode()
    object ByTitleDescending : SortCode()
    object BySalaryAscending : SortCode()
    object BySalaryDescending : SortCode()
    object ByExperienceAscending : SortCode()
    object ByExperienceDescending : SortCode()
}