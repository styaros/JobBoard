package com.example.jobboard.data.api.models

import com.google.gson.annotations.SerializedName

data class EmployeeModel(
    @SerializedName("id") val id: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("cvLink") val cvLink: String
)