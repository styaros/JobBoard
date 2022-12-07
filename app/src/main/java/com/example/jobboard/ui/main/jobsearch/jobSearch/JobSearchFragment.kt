package com.example.jobboard.ui.main.jobsearch.jobSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jobboard.R
import com.example.jobboard.data.api.models.CategoryApiModel
import com.example.jobboard.data.api.models.JobApiModel
import com.example.jobboard.data.api.models.LocationApiModel
import com.example.jobboard.databinding.BottomSheetFilterBinding
import com.example.jobboard.databinding.BottomSheetSortBinding
import com.example.jobboard.databinding.FragmentJobSearchBinding
import com.example.jobboard.ui.main.jobsearch.jobSearch.adapter.CategoryAdapter
import com.example.jobboard.ui.main.jobsearch.jobSearch.adapter.ExperienceAdapter
import com.example.jobboard.ui.main.jobsearch.jobSearch.adapter.JobAdapter
import com.example.jobboard.ui.main.jobsearch.jobSearch.adapter.LocationAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class JobSearchFragment : Fragment() {

    private lateinit var binding: FragmentJobSearchBinding

    private val viewModel by viewModel<JobSearchViewModel>()

    private val categoryList = mutableListOf<CategoryApiModel>()
    private val locationList = mutableListOf<LocationApiModel>()
    private val experienceList = mutableListOf<Int>()

    private val jobAdapter by lazy {
        JobAdapter(::onItemClickListener)
    }

    private val categoryAdapter by lazy {
        CategoryAdapter(::onCategoryCheckboxClickListener)
    }

    private val locationAdapter by lazy {
        LocationAdapter(::onLocationCheckboxListener)
    }

    private val experienceAdapter by lazy {
        ExperienceAdapter(::onExperienceCheckboxListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvJobs.adapter = jobAdapter

        binding.sortLayout.setOnClickListener {
            showSortBottomSheet()
        }

        binding.filterLayout.setOnClickListener {
            showFilterBottomSheet()
        }

        binding.ivSearch.setOnClickListener {
            val keyword = binding.etJobSearch.text.toString()
            if(keyword.isNotBlank()) {
                viewModel.searchByKeyword(keyword)
            } else {
                viewModel.searchByKeyword(null)
            }
        }

        setupObservers()
        viewModel.getJobsByFilter()
        viewModel.getFilters()

        experienceAdapter.submitList(listOf(1, 2, 3, 4, 5))
    }

    private fun showSortBottomSheet() {
        val sortBinding = BottomSheetSortBinding.inflate(
            LayoutInflater.from(requireContext()),
            binding.root,
            false
        )


        val sortDialogSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)

        sortDialogSheet.setContentView(sortBinding.root)

        sortBinding.btnSortAccepting.setOnClickListener {
            if(sortBinding.rbSortStandard.isChecked) {
                viewModel.setSort(SortCode.ByDefault)
            }
            if(sortBinding.rbSortByNameAscending.isChecked) {
                viewModel.setSort(SortCode.ByTitleAscending)
            }
            if(sortBinding.rbSortByNameDescending.isChecked) {
                viewModel.setSort(SortCode.ByTitleDescending)
            }
            if(sortBinding.rbSortBySalaryAscending.isChecked) {
                viewModel.setSort(SortCode.BySalaryAscending)
            }
            if(sortBinding.rbSortBySalaryDescending.isChecked) {
                viewModel.setSort(SortCode.BySalaryAscending)
            }
            if(sortBinding.rbSortByExperienceAscending.isChecked) {
                viewModel.setSort(SortCode.ByExperienceAscending)
            }
            if(sortBinding.rbSortByExperienceDescending.isChecked) {
                viewModel.setSort(SortCode.ByExperienceDescending)
            }
            sortDialogSheet.dismiss()
        }

        sortDialogSheet.show()
    }

    private fun showFilterBottomSheet() {
        val filterBinding = BottomSheetFilterBinding.inflate(
            LayoutInflater.from(requireContext()),
            binding.root,
            false
        )

        val filterDialogSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)

        filterDialogSheet.setContentView(filterBinding.root)

        filterBinding.categoryRecycler.adapter = categoryAdapter
        filterBinding.locationRecycler.adapter = locationAdapter
        filterBinding.experienceRecycler.adapter = experienceAdapter

        filterBinding.btnFilterAccepting.setOnClickListener {
            val categoryIds = categoryList.map { category -> category.id }
            val locationIds = locationList.map { location -> location.id }
            viewModel.setFilters(categoryIds, locationIds, experienceList)
            filterDialogSheet.dismiss()
        }

        filterDialogSheet.show()
    }

    private fun setupObservers() {
        viewModel.jobList.observe(viewLifecycleOwner) {
            jobAdapter.submitList(it)
        }
        viewModel.filterQuery.observe(viewLifecycleOwner) {
            viewModel.getJobsByFilter()
        }
        viewModel.categoryList.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }
        viewModel.locationList.observe(viewLifecycleOwner) {
            locationAdapter.submitList(it)
        }
    }

    private fun onItemClickListener(job: JobApiModel) {
        val bundle = bundleOf("jobId" to job.id)
        findNavController().navigate(R.id.jobDetailsFragment, bundle)
    }

    private fun onCategoryCheckboxClickListener(category: CategoryApiModel) {
        if(categoryList.contains(category)) {
            categoryList.remove(category)
        } else {
            categoryList.add(category)
        }
    }

    private fun onLocationCheckboxListener(location: LocationApiModel) {
        if(locationList.contains(location)) {
            locationList.remove(location)
        } else {
            locationList.add(location)
        }
    }

    private fun onExperienceCheckboxListener(experience: Int) {
        if(experienceList.contains(experience)) {
            experienceList.remove(experience)
        } else {
            experienceList.add(experience)
        }
    }
}