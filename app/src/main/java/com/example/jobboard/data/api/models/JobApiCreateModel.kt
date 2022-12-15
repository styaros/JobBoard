package com.example.jobboard.data.api.models

import com.google.gson.annotations.SerializedName

data class JobApiCreateModel(
    @SerializedName("name") val name: String,
    @SerializedName("discription") val description: String,
    @SerializedName("location") val location: String,
    @SerializedName("hours") val hours: Int,
    @SerializedName("salaryStart") val salaryStart: Int,
    @SerializedName("salaryEnd") val salaryEnd: Int,
    @SerializedName("experience") val experience: Int,
    @SerializedName("employment") val employment: String,
    @SerializedName("categoryId") val categoryId: String,
)