package com.quantumcode.napets.ui.signup.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quantumcode.napets.R
import com.quantumcode.napets.data.utils.validateEmail
import com.quantumcode.napets.data.utils.validatePassword
import com.quantumcode.napets.databinding.FragmentSignUpBinding
import com.quantumcode.napets.ui.base.BaseFragment
import com.quantumcode.napets.ui.main.viewmodel.MainViewModel
import com.quantumcode.napets.ui.signup.viewmodel.SigInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    private val viewModel: SigInViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override var isBottomNavVisible = View.GONE
    var isUsernameValid = false
    override fun getViewBinding() = FragmentSignUpBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setObservers() {
        viewModel.password.observe(viewLifecycleOwner) {
            binding.signUpLayoutPassword.error = it
        }

        viewModel.email.observe(viewLifecycleOwner) {
            binding.signUpLayoutEmail.error = it
        }

        viewModel.username.observe(viewLifecycleOwner) {
            binding.signUpLayoutUsername.error = it
        }
    }

    private fun setListeners() {
        binding.signUpButtonToLogin.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
        }

        binding.signUpButtonRegister.setOnClickListener {
            viewModel.validateCredentials(
                binding.signUpUsername.text.toString(),
                binding.signUpEmail.text.toString(),
                binding.signUpPassword.text.toString()
            )
        }

        binding.signUpPassword.doOnTextChanged { text, _, _, _ ->
            if (!text.toString().validatePassword()) {
                binding.signUpLayoutPassword.error = null
            }
        }

        binding.signUpUsername.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty()) {
                binding.signUpLayoutUsername.error = null
            }
        }

        binding.signUpEmail.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty() && text.toString().validateEmail()) {
                binding.signUpLayoutEmail.setEndIconDrawable(R.drawable.ic_baseline_check_circle_24)
                binding.signUpLayoutEmail.error = null
            } else {
                binding.signUpLayoutEmail.setEndIconDrawable(R.drawable.ic_outline_check_circle_24)
            }
        }
    }
}
