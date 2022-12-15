package com.example.jobboard.domain.repositories

import com.example.jobboard.data.api.models.EmployeeModel
import com.example.jobboard.data.api.models.EmployerModel
import com.example.jobboard.data.api.models.EmployerUpdateModel

interface UserInfoRepository {

    suspend fun getEmployerInfo(id: String): EmployerModel

    suspend fun sendEmployerUpdate(employer: EmployerUpdateModel)

    suspend fun getEmployeeInfo(id: String): EmployeeModel

    suspend fun sendEmployeeUpdate(employee: EmployeeModel)
}