package com.example.jobboard.domain.repositories

import com.example.jobboard.data.api.models.LocationApiModel

interface LocationRepository {

    suspend fun getAllLocations(): List<LocationApiModel>
}