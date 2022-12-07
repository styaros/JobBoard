package com.example.jobboard.data

import com.example.jobboard.data.api.JobApi
import com.example.jobboard.data.api.models.JobApiModel
import com.example.jobboard.data.api.models.JobDetailsApiModel
import com.example.jobboard.domain.models.Category
import com.example.jobboard.domain.models.Employer
import com.example.jobboard.domain.models.Job
import com.example.jobboard.domain.models.Location
import com.example.jobboard.domain.models.api.FilterQuery
import com.example.jobboard.domain.repositories.JobRepository

class JobRepositoryImpl(
    private val jobApi: JobApi
) : JobRepository {

    override suspend fun getAllJobs(): List<JobApiModel> {
        val request = jobApi.getJobsByFilters(FilterQuery())

        return request.body()?.jobs ?: emptyList()
    }

    override suspend fun getJobById(id: String): JobDetailsApiModel? {
        val request = jobApi.getJobById(id)

        return request.body()
    }

    override suspend fun getJobsByFilters(filters: FilterQuery): List<JobApiModel> {
        val request = jobApi.getJobsByFilters(filters)

        return request.body()?.jobs ?: emptyList()
    }
}