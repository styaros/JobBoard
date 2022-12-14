package com.example.jobboard.domain.repositories

import com.example.jobboard.data.api.models.CategoryApiModel

interface CategoryRepository {

    suspend fun getAllCategories(): List<CategoryApiModel>
}