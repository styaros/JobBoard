package com.example.jobboard.data.api

import com.example.jobboard.data.api.models.CategoryListApiModel
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApi {
    @GET("api/v$API_VERSION/Category/GetAll")
    suspend fun getAllCategories(): Response<CategoryListApiModel>
}