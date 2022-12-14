package com.example.jobboard.data.api

import retrofit2.Response
import retrofit2.http.*

interface AuthApi {
    @POST("auth/ULogin")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String,
    ): Response<String>

    @POST("auth/ULogin")
    suspend fun registerAsEmployee(
        @Query("email") email: String,
        @Query("password") password: String,
    ): Response<String>

    @POST("auth/ULogin")
    suspend fun registerAsEmployer(
        @Query("email") email: String,
        @Query("password") password: String,
    ): Response<String>
}