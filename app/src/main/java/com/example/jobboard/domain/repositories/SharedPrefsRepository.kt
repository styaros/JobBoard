package com.example.jobboard.domain.repositories

interface SharedPrefsRepository {

    suspend fun getProfileType(): Boolean

    suspend fun putProfileType(isEmployerProfile: Boolean)

    suspend fun getUserId(): String?

    suspend fun putUserId(userId: String?)
}