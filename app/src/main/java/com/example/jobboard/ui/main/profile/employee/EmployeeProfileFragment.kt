package com.example.jobboard.ui.main.profile.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jobboard.R
import com.example.jobboard.databinding.FragmentEmployeeProfileBinding
import com.example.jobboard.ui.main.profile.company.profile.CompanyProfileFragmentArgs
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmployeeProfileFragment : Fragment() {

    private lateinit var binding: FragmentEmployeeProfileBinding

    private val args: EmployeeProfileFragmentArgs by navArgs()

    private val viewModel: EmployeeProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeeProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(args.isEmployeeMainPage) {
            binding.ivBack.isVisible = false
        } else {
            binding.ivEdit.isVisible = false
            binding.layoutDeleteAccount.isVisible = false
            binding.layoutLogOut.isVisible = false
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.layoutLogOut.setOnClickListener {
            viewModel.logOut()
            findNavController().popBackStack(R.id.startFragment, false)
        }
    }
}