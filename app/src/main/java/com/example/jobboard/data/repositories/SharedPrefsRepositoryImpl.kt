package com.example.jobboard.data.repositories

import android.content.Context
import com.example.jobboard.domain.repositories.SharedPrefsRepository

class SharedPrefsRepositoryImpl(
    private val context: Context
) : SharedPrefsRepository {

    private val sharedPrefs = context.getSharedPreferences(USER_SHARED_PREFS, Context.MODE_PRIVATE)

    override suspend fun isEmployeeProfile(): Boolean {
        return sharedPrefs.getBoolean(IS_EMPLOYEE_PROFILE, false)
    }

    override suspend fun putProfileType(isEmployeeProfile: Boolean) {
        sharedPrefs.edit().putBoolean(IS_EMPLOYEE_PROFILE, isEmployeeProfile).apply()
    }

    override suspend fun getUserId(): String? {
        return sharedPrefs.getString(USER_ID, "")
    }

    override suspend fun putUserId(userId: String) {
        sharedPrefs.edit().putString(USER_ID, userId).apply()
    }

    companion object {
        private const val USER_SHARED_PREFS = "user_shared_prefs"
        private const val IS_EMPLOYEE_PROFILE = "is_employee_profile"
        private const val USER_ID = "user_id"
    }
}