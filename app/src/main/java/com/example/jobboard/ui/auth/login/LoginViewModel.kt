package com.example.jobboard.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.data.Authorization
import com.example.jobboard.domain.repositories.AuthRepository
import com.example.jobboard.domain.repositories.SharedPrefsRepository
import com.example.jobboard.domain.repositories.UserInfoRepository
import kotlinx.coroutines.launch
import com.example.jobboard.utils.AuthError
import com.example.jobboard.utils.validateEmail
import com.example.jobboard.utils.validatePassword

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userInfoRepository: UserInfoRepository,
    private val sharedPrefsRepository: SharedPrefsRepository
) : ViewModel() {

    private val _userIdLiveData = MutableLiveData<String>()
    val userIdLiveData: LiveData<String>
        get() = _userIdLiveData

    private val _emailErrorLiveData = MutableLiveData<AuthError>()
    val emailErrorLiveData: LiveData<AuthError>
        get() = _emailErrorLiveData

    private val _passwordErrorLiveData = MutableLiveData<AuthError>()
    val passwordErrorLiveData: LiveData<AuthError>
        get() = _passwordErrorLiveData

    fun login(email: String, password: String) {
        var error: AuthError? = null
        error = if(validEmail(email) is AuthError.NoErrors) null else validEmail(email)
        error = if(validPassword(password) is AuthError.NoErrors) null else validPassword(password)
        if(error == null) {
            viewModelScope.launch {
                _userIdLiveData.value = authRepository.login(email, password)
            }
        }
    }

    fun saveUserId(userId: String) {
        viewModelScope.launch {
            sharedPrefsRepository.putUserId(userId)
            try {
                userInfoRepository.getEmployerInfo(sharedPrefsRepository.getUserId() ?: "")
                sharedPrefsRepository.putProfileType(true)
                Authorization.isEmployerState = true
            } catch (e: Exception) {
                sharedPrefsRepository.putProfileType(false)
                Authorization.isEmployerState = false
            }
        }
    }

    private fun validEmail(email: String): AuthError {
        val result = validateEmail(email)
        _emailErrorLiveData.value = result
        return result
    }

    private fun validPassword(password: String): AuthError {
        val result = validatePassword(password)
        _passwordErrorLiveData.value = result
        return result
    }
}