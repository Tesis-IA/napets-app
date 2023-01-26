package com.example.napets.ui.landing.view

import com.example.napets.ui.base.BaseFragment
import com.example.napets.databinding.FragmentLandingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment : BaseFragment<FragmentLandingBinding>() {

    override fun getViewBinding() = FragmentLandingBinding.inflate(layoutInflater)

}