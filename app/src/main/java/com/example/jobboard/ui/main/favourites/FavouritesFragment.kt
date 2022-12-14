package com.example.jobboard.ui.main.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jobboard.data.api.models.CategoryApiModel
import com.example.jobboard.data.api.models.JobApiModel
import com.example.jobboard.data.api.models.LocationApiModel
import com.example.jobboard.databinding.FragmentFavouritesBinding
import com.example.jobboard.ui.main.favourites.adapter.FavouritesJobAdapter

class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding

    private val adapter by lazy {
        FavouritesJobAdapter(::onItemClickListener)
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

        binding.rvJobs.adapter = adapter
        adapter.submitList(listOf(
            JobApiModel(
                id = "sdfsdf",
                name = "work 1",
                location = LocationApiModel("id", "New York"),
                datePosted = "2022-12-07T02:14:29.185Z",
                employment = "full time",
                shortDescription = "short description",
                category = CategoryApiModel("id", "GameDev")
            ),
            JobApiModel(
                id = "sdfsdf",
                name = "work 2",
                location = LocationApiModel("id", "New York"),
                datePosted = "2022-12-07T02:14:29.185Z",
                employment = "full time",
                shortDescription = "short description",
                category = CategoryApiModel("id", "GameDev")
            )
        ))
    }

    private fun onItemClickListener(job: JobApiModel) {

    }
}