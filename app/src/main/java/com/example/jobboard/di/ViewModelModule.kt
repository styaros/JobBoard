package com.example.jobboard.di

import com.example.jobboard.ui.MainViewModel
import com.example.jobboard.ui.auth.start.StartViewModel
import com.example.jobboard.ui.auth.login.LoginViewModel
import com.example.jobboard.ui.main.jobsearch.jobDetails.JobDetailsViewModel
import com.example.jobboard.ui.main.jobsearch.jobSearch.JobSearchViewModel
import com.example.jobboard.ui.main.profile.company.edit.EditCompanyProfileViewModel
import com.example.jobboard.ui.main.profile.company.profile.CompanyProfileViewModel
import com.example.jobboard.ui.main.profile.employee.EmployeeProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainViewModel(get(), get())
    }

    viewModel {
        StartViewModel(get())
    }

    viewModel {
        LoginViewModel(get(), get(), get())
    }

    viewModel {
        CompanyProfileViewModel(get(), get())
    }

    viewModel {
        EmployeeProfileViewModel(get(), get())
    }

    viewModel {
        EditCompanyProfileViewModel(get(), get(), get())
    }

    viewModel {
        JobSearchViewModel(get(), get(), get())
    }

    viewModel {
        JobDetailsViewModel(get())
    }
}