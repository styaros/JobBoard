package com.example.jobboard.data.api

import com.example.jobboard.data.api.models.LocationListApiModel
import retrofit2.Response
import retrofit2.http.GET

interface LocationApi {
    @GET("api/v$API_VERSION/Location/GetAll")
    suspend fun getAllLocations(): Response<LocationListApiModel>
}