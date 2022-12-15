package com.example.jobboard.ui.main.favourites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.data.api.models.JobApiModel
import com.example.jobboard.domain.repositories.JobRepository
import com.example.jobboard.domain.repositories.SharedPrefsRepository
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val sharedPrefsRepository: SharedPrefsRepository,
    private val jobRepository: JobRepository
) : ViewModel() {

    private val _favouriteJobListLiveData = MutableLiveData<List<JobApiModel>>(listOf())
    val favouriteJobListLiveData: MutableLiveData<List<JobApiModel>>
        get() = _favouriteJobListLiveData

    fun getFavourites() {
        viewModelScope.launch {
            val userId = sharedPrefsRepository.getUserId()!!
            _favouriteJobListLiveData.value = jobRepository.getFavourites(userId)
        }
    }

    fun removeFromFavourites(jobId: String) {
        viewModelScope.launch {
            val list = favouriteJobListLiveData.value?.toMutableList()
            list?.removeAll { jobModel ->
                jobModel.id == jobId
            }
            _favouriteJobListLiveData.value = list!!
            val userId = sharedPrefsRepository.getUserId()!!
            jobRepository.deleteFromFavourites(jobId, userId)
        }
    }
}