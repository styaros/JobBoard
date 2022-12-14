package com.example.jobboard.data.api

import com.example.jobboard.data.api.models.EmployerModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserInfoApi {
    @GET("api/v$API_VERSION/Employer/Get/{id}")
    suspend fun getEmployerById(@Path("id") id: String): Response<EmployerModel>

    @PUT("api/v$API_VERSION/Employer/Update")
    suspend fun sendEmployerUpdate(@Body employer: EmployerModel): Response<EmployerModel>
}