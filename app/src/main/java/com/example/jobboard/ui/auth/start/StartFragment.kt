package com.example.jobboard.ui.auth.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jobboard.R
import com.example.jobboard.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        binding.btnRegisterAsEmployer.setOnClickListener {
            findNavController().navigate(R.id.jobSearchFragment)
        }

        binding.btnRegisterAsEmployee.setOnClickListener {
            findNavController().navigate(R.id.jobSearchFragment)
        }

        setupObservers()
    }

    private fun setupObservers() {

    }
}