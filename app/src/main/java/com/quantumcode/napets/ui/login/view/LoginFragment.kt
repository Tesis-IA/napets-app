package com.quantumcode.napets.ui.login.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quantumcode.napets.R
import com.quantumcode.napets.databinding.FragmentLoginBinding
import com.quantumcode.napets.ui.base.BaseFragment
import com.quantumcode.napets.ui.login.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.quantumcode.napets.data.utils.validateEmail
import com.quantumcode.napets.data.utils.validatePassword
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override var isBottomNavVisible = View.GONE

    private val viewModel: LoginViewModel by viewModels()

    override fun getViewBinding() = FragmentLoginBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setObservers() {
        viewModel.isAuthenticated.observe(viewLifecycleOwner) {
            // TODO: If it's true will should be navigate the next screen
        }

        viewModel.errorResponse.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it.toString(), Snackbar.LENGTH_LONG).show()
        }

        viewModel.password.observe(viewLifecycleOwner) {
            binding.loginLayoutInputPassword.error = it
        }

        viewModel.email.observe(viewLifecycleOwner) {
            binding.loginLayoutInputEmail.error = it
        }
    }

    private fun setListeners() {
        binding.loginButtonSignIn.setOnClickListener {
            viewModel.validateCredential(binding.loginTextEmail.text.toString(), binding.loginTextPassword.text.toString())
        }

        binding.loginButtonToRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }

        binding.loginTextEmail.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty() && text.toString().validateEmail()) {
                binding.loginLayoutInputEmail.setEndIconDrawable(R.drawable.ic_baseline_check_circle_24)
                binding.loginLayoutInputEmail.error = ""
            } else {
                binding.loginLayoutInputEmail.setEndIconDrawable(R.drawable.ic_outline_check_circle_24)
            }
        }

        binding.loginTextPassword.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty() && text.toString().validatePassword()) {
                clearError()
            }
        }
    }

    private fun clearError(){
        binding.loginLayoutInputEmail.error = ""
        binding.loginLayoutInputPassword.error = ""
    }
}