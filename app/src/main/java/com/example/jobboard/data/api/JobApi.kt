package com.example.jobboard.data.api

import com.example.jobboard.data.api.models.JobApiCreateModel
import com.example.jobboard.data.api.models.JobApiModel
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

    @POST("api/v$API_VERSION/Job/UCreate/UCreate")
    suspend fun createVacancy(
        @Query("UserId") userId: String,
        @Body job: JobApiCreateModel
    )

    @DELETE("api/v$API_VERSION/Job/UDelete/UDelete")
    suspend fun deleteVacancy(
        @Query("id") id: String,
        @Query("UserId") userId: String
    )

    @GET("api/v$API_VERSION/Job/UGetLikedJobs")
    suspend fun getFavourites(@Query("UserId") userId: String): Response<JobListApiModel>

    @POST("api/v$API_VERSION/Job/ULikeJob")
    suspend fun setToFavourites(@Query("jobId") jobId: String, @Query("UserId") userId: String)

    @DELETE("api/v$API_VERSION/Job/UUnlikeJob")
    suspend fun deleteFromFavourites(@Query("jobId") jobId: String, @Query("UserId") userId: String): Response<Unit>
}