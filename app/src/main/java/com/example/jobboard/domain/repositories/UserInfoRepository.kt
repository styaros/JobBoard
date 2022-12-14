package com.example.jobboard.domain.repositories

import com.example.jobboard.data.api.models.EmployerModel

interface UserInfoRepository {

    suspend fun getEmployerInfo(id: String): EmployerModel

    suspend fun sendEmployerUpdate(employer: EmployerModel)
}