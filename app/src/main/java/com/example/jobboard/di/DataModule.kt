package com.example.jobboard.di

import com.example.jobboard.data.CategoryRepositoryImpl
import com.example.jobboard.data.auth.AuthRepositoryImpl
import com.example.jobboard.data.JobRepositoryImpl
import com.example.jobboard.data.LocationRepositoryImpl
import com.example.jobboard.data.api.BASE_URL
import com.example.jobboard.data.api.CategoryApi
import com.example.jobboard.data.api.JobApi
import com.example.jobboard.data.api.LocationApi
import com.example.jobboard.domain.repositories.AuthRepository
import com.example.jobboard.domain.repositories.CategoryRepository
import com.example.jobboard.domain.repositories.JobRepository
import com.example.jobboard.domain.repositories.LocationRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    factory<AuthRepository> {
        AuthRepositoryImpl()
    }

    factory<JobRepository> {
        JobRepositoryImpl(get())
    }

    factory<CategoryRepository> {
        CategoryRepositoryImpl(get())
    }

    factory<LocationRepository> {
        LocationRepositoryImpl(get())
    }

    single<Retrofit> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<JobApi> {
        (get() as Retrofit).create(JobApi::class.java)
    }

    single<CategoryApi> {
        (get() as Retrofit).create(CategoryApi::class.java)
    }

    single<LocationApi> {
        (get() as Retrofit).create(LocationApi::class.java)
    }
}