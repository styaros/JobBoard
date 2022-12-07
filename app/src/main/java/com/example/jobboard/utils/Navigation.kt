package com.example.jobboard.utils

import android.content.Context
private const val USER_SHARED_PREFS = "user_shared_prefs"
private const val IS_EMPLOYEE_PROFILE = "is_employee_profile"

fun isEmployeeProfile(context: Context): Boolean {
    val sharedPrefs = context.getSharedPreferences(USER_SHARED_PREFS, Context.MODE_PRIVATE)
    return sharedPrefs.getBoolean(IS_EMPLOYEE_PROFILE, false)
}

fun putProfileType(context: Context, isEmployeeProfile: Boolean) {
    val sharedPrefs = context.getSharedPreferences(USER_SHARED_PREFS, Context.MODE_PRIVATE)
    sharedPrefs.edit().putBoolean(IS_EMPLOYEE_PROFILE, isEmployeeProfile).apply()
}