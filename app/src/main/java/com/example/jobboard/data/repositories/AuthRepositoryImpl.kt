package com.example.jobboard.data.repositories

import com.example.jobboard.data.api.AuthApi
import com.example.jobboard.domain.repositories.AuthRepository
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.RegistrationRequest
import net.openid.appauth.TokenRequest

class AuthRepositoryImpl(
    private val authApi: AuthApi
) : AuthRepository {

    override suspend fun login(email: String, password: String): String? {
        val request = authApi.login(email, password)

        return request.body()
    }
}