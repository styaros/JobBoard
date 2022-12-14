package com.example.jobboard.domain.repositories

interface SharedPrefsRepository {

    suspend fun isEmployeeProfile(): Boolean

    suspend fun putProfileType(isEmployeeProfile: Boolean)

    suspend fun getUserId(): String?

    suspend fun putUserId(userId: String)
}