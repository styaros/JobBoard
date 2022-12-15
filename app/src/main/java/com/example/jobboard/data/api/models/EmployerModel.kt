package com.example.jobboard.data.api.models

import com.google.gson.annotations.SerializedName

data class EmployerModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("aboutUs") val aboutUs: String,
    @SerializedName("teamSize") val teamSize: Int,
    @SerializedName("location") val location: String,
    @SerializedName("photoLink") val photoLink: String,
    @SerializedName("jobs") val jobs: JobListApiModel,
)

data class EmployerUpdateModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("aboutUs") val aboutUs: String,
    @SerializedName("teamSize") val teamSize: Int,
    @SerializedName("location") val location: String,
    @SerializedName("photoLink") val photoLink: String,
)