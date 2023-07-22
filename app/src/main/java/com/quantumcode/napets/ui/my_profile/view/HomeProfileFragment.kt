package com.quantumcode.napets.ui.my_profile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quantumcode.napets.R
import com.quantumcode.napets.databinding.FragmentHomeProfileBinding
import com.quantumcode.napets.ui.base.BaseFragment

class HomeProfileFragment : BaseFragment<FragmentHomeProfileBinding>() {
    override fun getViewBinding() = FragmentHomeProfileBinding.inflate(layoutInflater)

}