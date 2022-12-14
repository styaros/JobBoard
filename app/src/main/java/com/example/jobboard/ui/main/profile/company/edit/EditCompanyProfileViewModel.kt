package com.example.jobboard.ui.main.profile.company.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.data.api.models.EmployerModel
import com.example.jobboard.data.api.models.LocationApiModel
import com.example.jobboard.domain.repositories.LocationRepository
import com.example.jobboard.domain.repositories.SharedPrefsRepository
import com.example.jobboard.domain.repositories.UserInfoRepository
import kotlinx.coroutines.launch

class EditCompanyProfileViewModel(
    private val userInfoRepository: UserInfoRepository,
    private val sharedPrefsRepository: SharedPrefsRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _userInfoLiveData = MutableLiveData<EmployerModel>()
    val userInfoLiveData: LiveData<EmployerModel>
        get() = _userInfoLiveData

    private val _updateQueryLiveData = MutableLiveData<EmployerModel>()
    val updateQueryLiveData: LiveData<EmployerModel>
        get() = _updateQueryLiveData

    private val _locationListLiveData = MutableLiveData<List<LocationApiModel>>()
    val locationListLiveData: LiveData<List<LocationApiModel>>
        get() = _locationListLiveData

    fun sendInfoUpdate() {
        viewModelScope.launch {
            userInfoRepository.sendEmployerUpdate(updateQueryLiveData.value!!)
        }
    }

    fun setInfo(name: String, teamSize: Int, aboutUs: String) {
        _updateQueryLiveData.value = _updateQueryLiveData.value?.copy(
            name = name,
            teamSize = teamSize,
            aboutUs = aboutUs
        )
    }

    fun getUserInfo() {
        viewModelScope.launch {
            val userId = sharedPrefsRepository.getUserId()
            val employeeInfo = userInfoRepository.getEmployerInfo(userId!!)
            _userInfoLiveData.value = employeeInfo
            _updateQueryLiveData.value = employeeInfo
        }
    }

    fun getLocations() {
        viewModelScope.launch {
            _locationListLiveData.value = locationRepository.getAllLocations()
        }
    }

    fun setLocation(location: LocationApiModel) {
        viewModelScope.launch {
            _updateQueryLiveData.value = _updateQueryLiveData.value?.copy(
                location = location.id
            )
        }
    }
}