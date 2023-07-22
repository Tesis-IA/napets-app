package com.quantumcode.napets.ui.login.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quantumcode.napets.databinding.FragmentLoginBinding
import com.quantumcode.napets.ui.base.BaseFragment
import com.quantumcode.napets.ui.login.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.quantumcode.napets.data.utils.validatePassword
import com.quantumcode.napets.ui.main.view.MainActivity
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
            if(it) {
                (activity as MainActivity).navigateToHome()
            }
        }

        viewModel.username.observe(viewLifecycleOwner) {
            binding.loginLayoutInputUsername.error = it
        }

        viewModel.password.observe(viewLifecycleOwner) {
            binding.loginLayoutInputPassword.error = it
        }

        viewModel.errorResponse.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it.toString(), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun setListeners() {
        binding.loginButtonSignIn.setOnClickListener {
            viewModel.validateCredentials(
                binding.loginTextUsername.text.toString(),
                binding.loginTextPassword.text.toString()
            )
        }

        binding.loginButtonToRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }

        binding.loginTextPassword.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty() && text.toString().validatePassword()) {
                clearError()
            }
        }
    }

    private fun clearError(){
        binding.loginLayoutInputPassword.error = null
    }
}
