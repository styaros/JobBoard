package com.example.jobboard.ui.main.profile.company.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jobboard.R
import com.example.jobboard.data.api.models.LocationApiModel
import com.example.jobboard.databinding.FragmentEditCompanyProfileBinding
import com.example.jobboard.ui.main.profile.company.edit.adapter.LocationAdapter
import com.example.jobboard.utils.src
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditCompanyProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditCompanyProfileBinding

    private val viewModel: EditCompanyProfileViewModel by viewModel()

    private val adapter by lazy {
        LocationAdapter(::onItemClickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditCompanyProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLocations()
        viewModel.getUserInfo()

        binding.locationsLayout.setOnClickListener {
            if (binding.locationRecycler.isVisible) {
                binding.locationRecycler.isVisible = false
                binding.ivArrowLocations.src(R.drawable.ic_arrow_down)
            } else {
                binding.locationRecycler.isVisible = true
                binding.ivArrowLocations.src(R.drawable.ic_arrow_up)
            }
        }

        binding.locationRecycler.adapter = adapter

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnSave.setOnClickListener {
            val companyName = binding.etCompanyName.text.toString()
            val teamSize = binding.etTeamSize.text.toString().toInt()
            val aboutUs = binding.etAboutUs.text.toString()
            viewModel.setInfo(companyName, teamSize, aboutUs)
            viewModel.sendInfoUpdate()
            findNavController().navigateUp()
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.userInfoLiveData.observe(viewLifecycleOwner) { employer ->
            binding.etCompanyName.setText(employer.name)
            binding.etTeamSize.setText(employer.teamSize.toString())
            binding.etAboutUs.setText(employer.aboutUs)
            binding.tvLocations.text = employer.location
            adapter.currentList.forEach {
                if(it.city == employer.location) {
                    viewModel.setLocation(it)
                }
            }
        }
        viewModel.locationListLiveData.observe(viewLifecycleOwner) { locationList ->
            adapter.submitList(locationList)
        }
    }

    private fun onItemClickListener(location: LocationApiModel) {
        viewModel.setLocation(location)
        binding.tvLocations.text = location.city
        binding.locationRecycler.isVisible = false
        binding.ivArrowLocations.src(R.drawable.ic_arrow_down)
    }
}