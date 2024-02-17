package com.quantumcode.napets.ui.pestDisease.view

import com.quantumcode.napets.databinding.FragmentPestDiseaseFragmentBinding
import com.quantumcode.napets.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PestDiseaseFragment : BaseFragment<FragmentPestDiseaseFragmentBinding>() {
    override fun getViewBinding() = FragmentPestDiseaseFragmentBinding.inflate(layoutInflater)

}
