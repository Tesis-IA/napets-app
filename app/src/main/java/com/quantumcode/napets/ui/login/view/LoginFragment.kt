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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override var isBottomNavVisible = View.GONE

    private val viewModel: LoginViewModel by viewModels()
    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false

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
    }

    private fun setListeners() {
        binding.loginButtonSignIn.setOnClickListener {
            when {
                !isEmailValid && !isPasswordValid -> {
                    binding.loginLayoutInputEmail.error = "Ingrese un correo válido"
                    binding.loginLayoutInputPassword.error =
                        "La contraseña debe tener un mínimo de 8 caracteres"
                }
                !isEmailValid -> {
                    binding.loginLayoutInputEmail.error = "Ingrese un correo válido"
                }
                !isPasswordValid -> {
                    binding.loginLayoutInputPassword.error =
                        "La contraseña debe tener un mínimo de 8 caracteres"
                }
                else -> {
                    viewModel.login(
                        binding.loginTextEmail.text.toString(),
                        binding.loginTextPassword.text.toString()
                    )
                    clearError()
                }
            }
        }

        binding.loginButtonToRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }

        binding.loginTextEmail.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty()) {
                isEmailValid = if (text.contains("@") && text.contains(".com")) {
                    binding.loginLayoutInputEmail.setEndIconDrawable(R.drawable.ic_baseline_check_circle_24)
                    binding.loginLayoutInputEmail.error = ""
                    true
                } else {
                    binding.loginLayoutInputEmail.setEndIconDrawable(R.drawable.ic_outline_check_circle_24)
                    false
                }
            }
        }

        binding.loginTextPassword.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty()) {
                isPasswordValid = text.length >= 8
                if(isPasswordValid){
                    binding.loginLayoutInputPassword.error = ""
                }
            }
        }
    }

    private fun clearError(){
        binding.loginLayoutInputEmail.error = ""
        binding.loginLayoutInputPassword.error = ""
    }
}