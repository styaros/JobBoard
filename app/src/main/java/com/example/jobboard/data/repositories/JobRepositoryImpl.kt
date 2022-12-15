package com.example.jobboard.data.repositories

import com.example.jobboard.data.api.JobApi
import com.example.jobboard.data.api.models.JobApiCreateModel
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

    override suspend fun createJob(userId: String, job: JobApiCreateModel) {
        jobApi.createVacancy(userId, job)
    }

    override suspend fun deleteJob(jobId: String, userId: String) {
        jobApi.deleteVacancy(jobId, userId)
    }

    override suspend fun getFavourites(userId: String): List<JobApiModel> {
        val request = jobApi.getFavourites(userId)

        return request.body()?.jobs ?: listOf()
    }

    override suspend fun setToFavourites(jobId: String, userId: String) {
        jobApi.setToFavourites(jobId, userId)
    }

    override suspend fun deleteFromFavourites(jobId: String, userId: String) {
        jobApi.deleteFromFavourites(jobId, userId)
    }
}