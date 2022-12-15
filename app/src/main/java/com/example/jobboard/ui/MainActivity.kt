package com.example.jobboard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.example.jobboard.R
import com.example.jobboard.data.Authorization
import com.example.jobboard.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getEmployeeInfo()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.job_search -> {
                    navController.navigate(R.id.jobSearchFragment)
                    true
                }
                R.id.favourites -> {
                    navController.navigate(R.id.favouritesFragment)
                    true
                }
                R.id.profile -> {
                    GlobalScope.launch {
                        if (Authorization.isEmployerFlow.first()) {
                            withContext(Dispatchers.Main) {
                                val bundle = bundleOf("isCompanyMainPage" to true)
                                navController.navigate(R.id.companyProfileFragment, bundle)
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                val bundle = bundleOf("isEmployeeMainPage" to true)
                                navController.navigate(R.id.employeeProfileFragment, bundle)
                            }
                        }
                    }

                    true
                }
                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.startFragment, R.id.loginFragment -> {
                    showBottomBar(false)
                }
                else -> showBottomBar(true)
            }
        }

        setupObservers()
    }

    private fun showBottomBar(show: Boolean) {
        binding.bottomNav.isVisible = show
    }

    private fun setupObservers() {
        GlobalScope.launch {
            Authorization.isEmployerFlow.collect { isEmployer ->
                if (isEmployer) {
                    withContext(Dispatchers.Main) {
                        binding.bottomNav.menu.clear()
                        binding.bottomNav.inflateMenu(R.menu.bottom_nav_menu_employer)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        binding.bottomNav.menu.clear()
                        binding.bottomNav.inflateMenu(R.menu.bottom_nav_menu_employee)
                    }
                }
            }
        }
    }
}