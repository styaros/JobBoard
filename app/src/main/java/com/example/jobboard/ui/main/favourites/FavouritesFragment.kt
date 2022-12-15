package com.example.jobboard.ui.main.favourites

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
import com.example.jobboard.databinding.FragmentFavouritesBinding
import com.example.jobboard.ui.main.favourites.adapter.FavouritesJobAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding

    private val viewModel: FavouritesViewModel by viewModel()

    private val adapter by lazy {
        FavouritesJobAdapter(::onItemClickListener, ::onRemoveFromFavouriteClickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavourites()

        binding.rvJobs.adapter = adapter

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.favouriteJobListLiveData.observe(viewLifecycleOwner) { jobList ->
            adapter.submitList(jobList)
        }
    }

    private fun onItemClickListener(job: JobApiModel) {
        val bundle = bundleOf(
            "jobId" to job.id,
            "employerId" to job.employer.id
        )
        findNavController().navigate(R.id.jobDetailsFragment, bundle)
    }

    private fun onRemoveFromFavouriteClickListener(job: JobApiModel) {
        viewModel.removeFromFavourites(job.id)
    }
}