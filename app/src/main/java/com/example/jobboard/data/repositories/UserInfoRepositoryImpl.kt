package com.example.jobboard.data.repositories

import com.example.jobboard.data.api.UserInfoApi
import com.example.jobboard.data.api.models.EmployerModel
import com.example.jobboard.domain.repositories.UserInfoRepository

class UserInfoRepositoryImpl(
    private val userInfoApi: UserInfoApi
) : UserInfoRepository {

    override suspend fun getEmployerInfo(id: String): EmployerModel {
        val request = userInfoApi.getEmployerById(id)

        return request.body()!!
    }

    override suspend fun sendEmployerUpdate(employer: EmployerModel) {
        userInfoApi.sendEmployerUpdate(employer)
    }
}