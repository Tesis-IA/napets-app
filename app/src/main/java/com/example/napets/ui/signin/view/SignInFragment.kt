package com.example.napets.ui.signin.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.napets.databinding.FragmentSignInBinding
import com.example.napets.ui.base.BaseFragment
import com.example.napets.ui.main.viewmodel.MainViewModel
import com.example.napets.ui.signin.viewmodel.SigInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    private val viewModel: SigInViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun getViewBinding() = FragmentSignInBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setObservers() {

    }

    private fun setListeners() {

    }

}