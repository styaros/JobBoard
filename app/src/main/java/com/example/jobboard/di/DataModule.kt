package com.example.jobboard.di

import com.example.jobboard.data.api.*
import com.example.jobboard.data.api.retrofit.RetrofitAuthInstance
import com.example.jobboard.data.api.retrofit.RetrofitInstance
import com.example.jobboard.data.repositories.*
import com.example.jobboard.domain.repositories.*
import com.example.jobboard.ui.main.profile.company.employerVacancies.EmployerVacanciesViewModel
import org.koin.dsl.module

val dataModule = module {

    factory<AuthRepository> {
        AuthRepositoryImpl(get())
    }

    factory<UserInfoRepository> {
        UserInfoRepositoryImpl(get())
    }

    factory<SharedPrefsRepository> {
        SharedPrefsRepositoryImpl(get())
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

    single {
        RetrofitInstance()
    }

    single {
        RetrofitAuthInstance()
    }

    single<AuthApi> {
        (get() as RetrofitAuthInstance).getInstance().create(AuthApi::class.java)
    }

    single<UserInfoApi> {
        (get() as RetrofitInstance).getInstance().create(UserInfoApi::class.java)
    }

    single<JobApi> {
        (get() as RetrofitInstance).getInstance().create(JobApi::class.java)
    }

    single<CategoryApi> {
        (get() as RetrofitInstance).getInstance().create(CategoryApi::class.java)
    }

    single<LocationApi> {
        (get() as RetrofitInstance).getInstance().create(LocationApi::class.java)
    }
}