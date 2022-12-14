package com.example.jobboard.ui.auth.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.domain.repositories.SharedPrefsRepository
import kotlinx.coroutines.launch

class StartViewModel(
    private val sharedPrefsRepository: SharedPrefsRepository
) : ViewModel() {

    private val _isUserAuthorizedLiveData = MutableLiveData<Boolean>()
    val isUserAuthorizedLiveData: LiveData<Boolean>
        get() = _isUserAuthorizedLiveData

    fun isUserAuthorized() {
        viewModelScope.launch {
            _isUserAuthorizedLiveData.value = !sharedPrefsRepository.getUserId().isNullOrEmpty()
        }
    }
}