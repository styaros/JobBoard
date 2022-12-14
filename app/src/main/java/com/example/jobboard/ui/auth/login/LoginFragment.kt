package com.example.jobboard.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jobboard.R
import com.example.jobboard.data.Authorization
import com.example.jobboard.databinding.FragmentLoginBinding
import com.example.jobboard.utils.AuthError
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etEmail.doOnTextChanged { text, start, before, count ->
            binding.etEmailContainer.error = null
        }

        binding.etPassword.doOnTextChanged { text, start, before, count ->
            binding.etPasswordContainer.error = null
        }

        binding.btnLogin.setOnClickListener {
            viewModel.login(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.userIdLiveData.observe(viewLifecycleOwner) { userId ->
            if(userId.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Such user does not exist", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.saveUserId(userId)
                    findNavController().navigate(R.id.jobSearchFragment)
                }
            }

        viewModel.emailErrorLiveData.observe(viewLifecycleOwner) { error ->
            if(error is AuthError.EmptyEmail) {
                binding.etEmailContainer.error = "Email is empty"
            }
            if(error is AuthError.WrongEmail) {
                binding.etEmailContainer.error = "Wrong email"
            }
        }
        viewModel.passwordErrorLiveData.observe(viewLifecycleOwner) { error ->
            if(error is AuthError.EmptyPassword) {
                binding.etPasswordContainer.error = "Password is empty"
            }
            if(error is AuthError.WrongPassword) {
                binding.etPasswordContainer.error = "Wrong password"
            }
            if(error is AuthError.LessThan8Characters) {
                binding.etPasswordContainer.error = "Password has less than 8 symbols"
            }
        }
    }
}