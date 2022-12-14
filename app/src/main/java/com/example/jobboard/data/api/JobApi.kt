package com.example.jobboard.data.api

import com.example.jobboard.data.api.models.JobDetailsApiModel
import com.example.jobboard.data.api.models.JobListApiModel
import com.example.jobboard.domain.models.api.FilterQuery
import retrofit2.Response
import retrofit2.http.*

interface JobApi {
    @POST("api/v$API_VERSION/Job/GetAll")
    suspend fun getJobsByFilters(@Body filterQuery: FilterQuery): Response<JobListApiModel>

    @GET("api/v$API_VERSION/Job/Get/{id}")
    suspend fun getJobById(@Path("id") id: String): Response<JobDetailsApiModel>

    @GET("api/v$API_VERSION/Job/UGetAppliedJobs/UGetAppliedJobs")
    suspend fun getAppliedJobs(@Query("UserId") id: String): Response<JobDetailsApiModel>
}