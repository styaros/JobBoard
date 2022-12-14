package com.example.jobboard.ui.main.jobsearch.jobSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _categoryChoseList = MutableLiveData<List<CategoryApiModel>>(listOf())
    val categoryChoseList: LiveData<List<CategoryApiModel>>
        get() = _categoryChoseList

    private val _locationChoseList = MutableLiveData<List<LocationApiModel>>(listOf())
    val locationChoseList: LiveData<List<LocationApiModel>>
        get() = _locationChoseList

    private val _experienceChoseList = MutableLiveData<List<Int>>(listOf())
    val experienceChoseList: LiveData<List<Int>>
        get() = _experienceChoseList

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

    fun setCategories() {
        _filterQuery.value = _filterQuery.value?.copy(
            filters = _filterQuery.value?.filters?.copy(
                categoryIds = _categoryChoseList.value!!.map { it.id },
                locationIds = _locationChoseList.value!!.map { it.id },
                experiences = _experienceChoseList.value!!
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

    fun setSalary(salaryStart: Int, salaryEnd: Int) {
        _filterQuery.value = _filterQuery.value?.copy(
            filters = _filterQuery.value?.filters?.copy(
                salaryStart = salaryStart,
                salaryEnd = salaryEnd
            ) ?: Filters(
                salaryStart = 0,
                salaryEnd = 1000000
            )
        )
    }

    fun updateCategories(category: CategoryApiModel) {
        val list = _categoryChoseList.value!!.toMutableList()
         if (list.contains(category)) {
            list.remove(category)
        } else {
            list.add(category)
        }
        _categoryChoseList.value = list
    }

    fun updateLocations(location: LocationApiModel) {
        val list = _locationChoseList.value!!.toMutableList()
        if (list.contains(location)) {
            list.remove(location)
        } else {
            list.add(location)
        }
        _locationChoseList.value = list
    }

    fun updateExperiences(experience: Int) {
        val list = _experienceChoseList.value!!.toMutableList()
        if (list.contains(experience)) {
            list.remove(experience)
        } else {
            list.add(experience)
        }
        _experienceChoseList.value = list
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