package com.quantumcode.napets.ui.myCrops

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quantumcode.napets.R
import com.quantumcode.napets.databinding.FragmentMyCropsBinding
import com.quantumcode.napets.ui.base.BaseFragment

class MyCropsFragment : BaseFragment<FragmentMyCropsBinding>() {
    override fun getViewBinding() = FragmentMyCropsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}