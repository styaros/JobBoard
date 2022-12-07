package com.example.jobboard.data

import com.example.jobboard.data.api.LocationApi
import com.example.jobboard.data.api.models.LocationApiModel
import com.example.jobboard.domain.repositories.LocationRepository

class LocationRepositoryImpl(
    private val locationApi: LocationApi
) : LocationRepository {

    override suspend fun getAllLocations(): List<LocationApiModel> {
        val request = locationApi.getAllLocations()

        return request.body()?.locationList ?: emptyList()
    }
}