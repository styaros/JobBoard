package com.example.jobboard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.example.jobboard.R
import com.example.jobboard.databinding.ActivityMainBinding
import com.example.jobboard.ui.auth.start.StartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.startFragment, R.id.loginFragment -> {
                    showBottomBar(false)
                }
                else -> showBottomBar(true)
            }
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.job_search -> {
                    navController.navigate(R.id.jobSearchFragment)
                    true
                }
                R.id.favourites -> {
                    navController.navigate(R.id.favouritesFragment)
                    true
                }
                R.id.profile -> {
                    if(true) {
                        val bundle = bundleOf("isEmployeeMainPage" to true)
                        navController.navigate(R.id.employeeProfileFragment, bundle)
                    } else {
                        val bundle = bundleOf("isCompanyMainPage" to true)
                        navController.navigate(R.id.companyProfileFragment, bundle)
                    }

                    true
                }
                else -> false
            }
        }
    }

    private fun showBottomBar(show: Boolean) {
        binding.bottomNav.isVisible = show
    }
}