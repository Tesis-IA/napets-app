package com.example.napets.ui.login.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.napets.databinding.FragmentLoginBinding
import com.example.napets.ui.base.BaseFragment
import com.example.napets.ui.login.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
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
        viewModel.isAuthenticated.observe(viewLifecycleOwner){
            // TODO: If it's true will should be navigate the next screen
        }
        viewModel.errorResponse.observe(viewLifecycleOwner){
            Snackbar.make(binding.root, it.toString(), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun setListeners() {
        binding.loginButtonSignIn.setOnClickListener {
            viewModel.login(
                binding.loginTextEmail.text.toString(),
                binding.loginTextPassword.text.toString()
            )
        }

        binding.loginButtonToRegister.setOnClickListener {
            findNavController().navigate()
        }
    }



}