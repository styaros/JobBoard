package com.example.jobboard.di

import com.example.jobboard.ui.auth.start.StartViewModel
import com.example.jobboard.ui.auth.login.LoginViewModel
import com.example.jobboard.ui.main.jobsearch.jobDetails.JobDetailsViewModel
import com.example.jobboard.ui.main.jobsearch.jobSearch.JobSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        StartViewModel(get())
    }

    viewModel {
        LoginViewModel(get(), get())
    }

    viewModel {
        JobSearchViewModel(get(), get(), get())
    }

    viewModel {
        JobDetailsViewModel(get())
    }
}