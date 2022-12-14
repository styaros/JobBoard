package com.example.jobboard.ui.main.profile.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.data.api.models.EmployerModel
import com.example.jobboard.domain.repositories.SharedPrefsRepository
import com.example.jobboard.domain.repositories.UserInfoRepository
import kotlinx.coroutines.launch

class EmployeeProfileViewModel(
    private val userInfoRepository: UserInfoRepository,
    private val sharedPrefsRepository: SharedPrefsRepository
) : ViewModel() {

    private val _userInfoLiveData = MutableLiveData<EmployerModel>()
    val userInfoLiveData: LiveData<EmployerModel>
        get() = _userInfoLiveData

//    fun getUserInfo() {
//        viewModelScope.launch {
//            val userId = sharedPrefsRepository.getUserId()
//            _userInfoLiveData.value = userInfoRepository.getEmployerInfo(userId!!)
//        }
//    }

    fun logOut() {
        viewModelScope.launch {
            sharedPrefsRepository.putUserId(null)
        }
    }
}