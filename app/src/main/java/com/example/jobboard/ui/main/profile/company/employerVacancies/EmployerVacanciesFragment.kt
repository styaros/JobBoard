package com.example.jobboard.ui.main.profile.company.employerVacancies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jobboard.R
import com.example.jobboard.data.api.models.JobApiModel
import com.example.jobboard.data.api.models.VacancyApiModel
import com.example.jobboard.databinding.FragmentVacanciesBinding
import com.example.jobboard.ui.main.profile.company.employerVacancies.adapter.VacancyAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmployerVacanciesFragment : Fragment() {

    private lateinit var binding: FragmentVacanciesBinding

    private val adapter by lazy {
        VacancyAdapter(::onEditClickListener, ::onDeleteClickListener)
    }

    private val viewModel: EmployerVacanciesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVacanciesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getJobs()

        binding.rvVacancies.adapter = adapter

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnAddTask.setOnClickListener {
            findNavController().navigate(R.id.addVacancyFragment)
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.vacancyListLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun onEditClickListener(job: VacancyApiModel) {

    }

    private fun onDeleteClickListener(job: VacancyApiModel) {
        viewModel.deleteVacancy(job.id)
    }
}