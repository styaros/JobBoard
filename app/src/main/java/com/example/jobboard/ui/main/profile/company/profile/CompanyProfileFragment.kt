package com.example.jobboard.ui.main.profile.company.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jobboard.databinding.FragmentCompanyProfileBinding

class CompanyProfileFragment : Fragment() {

    private lateinit var binding: FragmentCompanyProfileBinding

    private val args: CompanyProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanyProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(args.isCompanyMainPage) {
            binding.ivBack.isVisible = false
        } else {
            binding.ivEdit.isVisible = false
            binding.layoutDeleteAccount.isVisible = false
            binding.layoutLogOut.isVisible = false
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}