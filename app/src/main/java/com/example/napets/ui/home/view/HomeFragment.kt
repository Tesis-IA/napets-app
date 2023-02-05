package com.example.napets.ui.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.napets.R
import com.example.napets.databinding.FragmentHomeBinding
import com.example.napets.ui.base.BaseFragment
import com.example.napets.ui.home.viewmodel.HomeViewModel
import com.example.napets.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

}