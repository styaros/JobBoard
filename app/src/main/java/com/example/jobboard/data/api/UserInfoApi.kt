package com.example.jobboard.data.api

import com.example.jobboard.data.api.models.EmployeeModel
import com.example.jobboard.data.api.models.EmployerModel
import com.example.jobboard.data.api.models.EmployerUpdateModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserInfoApi {
    @GET("api/v$API_VERSION/Employer/Get/{id}")
    suspend fun getEmployerById(@Path("id") id: String): Response<EmployerModel>

    @PUT("api/v$API_VERSION/Employer/Update")
    suspend fun sendEmployerUpdate(@Body employer: EmployerUpdateModel): Response<Unit>

    @GET("api/v$API_VERSION/Employee/UGet")
    suspend fun getEmployeeById(@Query("UserId") id: String): Response<EmployeeModel>

    @PUT("api/v$API_VERSION/Employee/Update")
    suspend fun sendEmployeeUpdate(@Body employer: EmployeeModel)
}