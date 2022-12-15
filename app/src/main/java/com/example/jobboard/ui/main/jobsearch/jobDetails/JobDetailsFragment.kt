package com.example.jobboard.ui.main.jobsearch.jobDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jobboard.R
import com.example.jobboard.databinding.FragmentJobDetailInfoBinding
import com.example.jobboard.utils.ddMMyyyyFormatDate
import com.example.jobboard.utils.fullFormatDate
import com.example.jobboard.utils.src
import org.koin.androidx.viewmodel.ext.android.viewModel

class JobDetailsFragment : Fragment() {

    private lateinit var binding: FragmentJobDetailInfoBinding

    private val viewModel by viewModel<JobDetailsViewModel>()

    private val args: JobDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobDetailInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getJobById(args.jobId)

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.ivCompanyLogo.setOnClickListener {
            val bundle = bundleOf("employerId" to args.employerId)
            findNavController().navigate(R.id.companyProfileFragment, bundle)
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.jobDetails.observe(viewLifecycleOwner) { job ->
            val datePosted = fullFormatDate.parse(job.datePosted)
            val datePostedString = ddMMyyyyFormatDate.format(datePosted)

            binding.tvJobCity.text = job.location
            binding.tvJobDescription.text = job.description
            binding.tvJobExperience.text = requireActivity().getString(R.string.needed_experience, job.experience.toString())
            binding.tvJobPostedDate.text = datePostedString
            binding.tvJobVacation.text = job.name
            binding.ivCompanyLogo.src(R.drawable.random_logo)
        }
    }
}