package com.example.jobboard.ui.auth.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jobboard.R
import com.example.jobboard.databinding.FragmentStartBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    private val viewModel: StartViewModel by viewModel()

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

//        binding.btnRegisterAsEmployer.setOnClickListener {
//            findNavController().navigate(R.id.jobSearchFragment)
//        }
//
//        binding.btnRegisterAsEmployee.setOnClickListener {
//            findNavController().navigate(R.id.jobSearchFragment)
//        }

        setupObservers()
        viewModel.isUserAuthorized()
    }

    private fun setupObservers() {
        viewModel.isUserAuthorizedLiveData.observe(viewLifecycleOwner) { isUserAuthorized ->
            if(isUserAuthorized) {
                findNavController().navigate(R.id.jobSearchFragment)
            }
        }
    }
}