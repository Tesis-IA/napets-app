package com.quantumcode.napets.ui.signup.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quantumcode.napets.R
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
    var isEmailValid = false
    var isPasswordValid = false
    var isSamePassword = false
    override fun getViewBinding() = FragmentSignUpBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setObservers() {

    }

    private fun setListeners() {
        binding.signUpButtonToLogin.setOnClickListener {
            findNavController().navigate(com.quantumcode.napets.ui.signup.view.SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
        }

        binding.signUpButtonRegister.setOnClickListener {
            when {
                !isEmailValid && !isPasswordValid && !isUsernameValid -> {
                    binding.signUpLayoutEmail.error = "Ingrese un correo válido"
                    binding.signUpLayoutPassword.error =
                        "La contraseña debe tener un mínimo de 8 caracteres"
                    binding.signUpLayoutUsername.error =
                        "Campo requerido"
                }
                !isEmailValid -> {
                    binding.signUpLayoutEmail.error = "Ingrese un correo válido"
                }
                !isPasswordValid -> {
                    binding.signUpLayoutPassword.error =
                        "La contraseña debe tener un mínimo de 8 caracteres"
                }
                !isUsernameValid -> {
                    binding.signUpLayoutUsername.error =
                        "Campo requerido"
                }
                binding.signUpPassword.text.toString() != binding.signUpConfirmPassword.text.toString() -> {
                    binding.signUpLayoutConfirmPassword.error =
                        "Las contraseñas no coinciden"
                }
                else -> {
                    viewModel.registerUSer(
                        username = binding.signUpUsername.text.toString(),
                        email = binding.signUpEmail.text.toString(),
                        password = binding.signUpPassword.text.toString()
                    )
                    clearError()
                }
            }
        }

        binding.signUpUsername.doOnTextChanged { text, _, _, _ ->
            isUsernameValid = !text.isNullOrEmpty()
            if (isUsernameValid) binding.signUpLayoutUsername.error = ""
        }

        binding.signUpEmail.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty()) {
                isEmailValid = if (text.contains("@") && text.contains(".com")) {
                    binding.signUpLayoutEmail.setEndIconDrawable(R.drawable.ic_baseline_check_circle_24)
                    binding.signUpLayoutEmail.error = ""
                    true
                } else {
                    binding.signUpLayoutEmail.setEndIconDrawable(R.drawable.ic_outline_check_circle_24)
                    false
                }
            }
        }

        binding.signUpPassword.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty()) {
                isPasswordValid = text.length >= 8
                if (isPasswordValid) {
                    binding.signUpLayoutPassword.error = ""
                }
            }
        }
    }

    private fun clearError() {
        binding.signUpLayoutEmail.error = ""
        binding.signUpLayoutPassword.error = ""
        binding.signUpLayoutConfirmPassword.error = ""
    }

}