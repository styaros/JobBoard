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
import com.example.jobboard.databinding.FragmentAddVacancyBinding
import com.example.jobboard.ui.main.profile.company.addVacancy.adapter.CategoryAdapter
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

        viewModel.getCategories()

        binding.categoryRecycler.adapter = adapter

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

        experienceAdapter.addAll(0, 1, 2, 3, 4, 5)

        binding.ivConfirm.setOnClickListener {
            val name = binding.etName.text.toString()
            val description = binding.etDescription.text.toString()
            val location = binding.etLocation.text.toString()
            val salaryStart = binding.etSalaryStart.text.toString().toInt()
            val salaryEnd = binding.etSalaryEnd.text.toString().toInt()
            val experience = binding.spinnerExperience.selectedItem as Int
            viewModel.addVacancy(name, description, location, salaryStart, salaryEnd, experience)

        }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.categoryListLiveData.observe(viewLifecycleOwner) { categories ->
            adapter.submitList(categories)
            viewModel.refreshCategory(categories[0])
            binding.tvCategories.text = categories[0].name
        }
        viewModel.ping.observe(viewLifecycleOwner) {
            if(it) {
                findNavController().navigateUp()
            }
        }
    }

    private fun onItemClickListener(category: CategoryApiModel) {
        binding.tvCategories.text = category.name
        binding.categoryRecycler.isVisible = false
        viewModel.refreshCategory(category)
    }
}