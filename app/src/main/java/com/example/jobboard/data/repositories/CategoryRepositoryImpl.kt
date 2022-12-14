package com.example.jobboard.data.repositories

import com.example.jobboard.data.api.CategoryApi
import com.example.jobboard.data.api.models.CategoryApiModel
import com.example.jobboard.domain.repositories.CategoryRepository

class CategoryRepositoryImpl(
    private val categoryApi: CategoryApi
) : CategoryRepository {

    override suspend fun getAllCategories(): List<CategoryApiModel> {
        val request = categoryApi.getAllCategories()

        return request.body()?.categoryList ?: emptyList()
    }
}