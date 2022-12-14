package com.example.jobboard.domain.repositories

interface AuthRepository {

    suspend fun login(email: String, password: String): String?
}