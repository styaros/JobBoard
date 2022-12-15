package com.example.jobboard.ui.main.profile.company.addVacancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jobboard.R
import com.example.jobboard.data.api.models.CategoryApiModel
import com.example.jobboard.data.api.models.LocationApiModel
import com.example.jobboard.databinding.FragmentAddVacancyBinding
import com.example.jobboard.databinding.FragmentEditCompanyProfileBinding
import com.example.jobboard.ui.main.profile.company.addVacancy.adapter.CategoryAdapter
import com.example.jobboard.ui.main.profile.company.edit.EditCompanyProfileViewModel
import com.example.jobboard.ui.main.profile.company.edit.adapter.LocationAdapter
import com.example.jobboard.utils.src
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddVacancyFragment : Fragment() {

    private lateinit var binding: FragmentAddVacancyBinding

    private val viewModel: AddVacancyViewModel by viewModel()

    private val adapter by lazy {
        CategoryAdapter(::onItemClickListener)
    }

    private val experienceAdapter by lazy {
        ArrayAdapter<Int>(requireContext(), android.R.layout.simple_spinner_dropdown_item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddVacancyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivArrowCategories.setOnClickListener {
            if (binding.categoryRecycler.isVisible) {
                binding.categoryRecycler.isVisible = false
                binding.ivArrowCategories.src(R.drawable.ic_arrow_down)
            } else {
                binding.categoryRecycler.isVisible = true
                binding.ivArrowCategories.src(R.drawable.ic_arrow_up)
            }
        }

        binding.spinnerExperience.adapter = experienceAdapter

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.categoryListLiveData.observe(viewLifecycleOwner) { categories ->
            adapter.submitList(categories)
        }
    }

    private fun onItemClickListener(category: CategoryApiModel) {
        binding.tvCategories.text = category.name
        binding.categoryRecycler.isVisible = false
    }
}