package com.example.jobboard.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.data.Authorization
import com.example.jobboard.domain.repositories.SharedPrefsRepository
import com.example.jobboard.domain.repositories.UserInfoRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val userInfoRepository: UserInfoRepository,
    private val sharedPrefsRepository: SharedPrefsRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            Authorization.refresh(sharedPrefsRepository.getProfileType())
        }
    }

    private val _isEmployerLiveData = MutableLiveData(false)
    val isEmployerLiveData: MutableLiveData<Boolean>
        get() = _isEmployerLiveData

    fun getEmployeeInfo() {
        viewModelScope.launch {
            try {
                userInfoRepository.getEmployerInfo(sharedPrefsRepository.getUserId() ?: "")
                sharedPrefsRepository.putProfileType(true)
                _isEmployerLiveData.value = true
            } catch (e: Exception) {
                _isEmployerLiveData.value = false
                sharedPrefsRepository.putProfileType(false)
            }
        }
    }
}