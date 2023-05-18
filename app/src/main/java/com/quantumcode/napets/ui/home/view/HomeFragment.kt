package com.quantumcode.napets.ui.home.view

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.quantumcode.napets.databinding.FragmentHomeBinding
import com.quantumcode.napets.ui.base.BaseFragment
import com.quantumcode.napets.ui.home.viewmodel.HomeViewModel
import com.quantumcode.napets.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

}