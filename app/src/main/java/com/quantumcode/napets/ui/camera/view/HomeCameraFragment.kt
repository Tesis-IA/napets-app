package com.quantumcode.napets.ui.camera.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quantumcode.napets.R
import com.quantumcode.napets.databinding.FragmentHomeCameraBinding
import com.quantumcode.napets.ui.base.BaseFragment

class HomeCameraFragment : BaseFragment<FragmentHomeCameraBinding>() {
    override fun getViewBinding() = FragmentHomeCameraBinding.inflate(layoutInflater)
}