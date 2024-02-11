package com.quantumcode.napets.ui.camera.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.quantumcode.napets.databinding.FragmentHomeCameraBinding
import com.quantumcode.napets.ui.base.BaseFragment

class HomeCameraFragment : BaseFragment<FragmentHomeCameraBinding>() {

    override fun getViewBinding() = FragmentHomeCameraBinding.inflate(layoutInflater)

    override fun createView(view: View, savedInstanceState: Bundle?) {
        super.createView(view, savedInstanceState)
    }

    override fun setListeners() {
        super.setListeners()
        binding.homeCameraButtonTakePicture.setOnClickListener {
            findNavController().navigate(HomeCameraFragmentDirections.actionHomeCameraFragmentToTakePictureFragment())
        }
    }

    override fun setObservers() {
        super.setObservers()
    }
}
