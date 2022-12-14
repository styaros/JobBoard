package com.example.jobboard.data.api.models

import com.google.gson.annotations.SerializedName

data class LocationListApiModel(
    @SerializedName("locations") val locationList: List<LocationApiModel>
)

data class LocationApiModel(
    @SerializedName("id") val id: String,
    @SerializedName("city") val city: String
)