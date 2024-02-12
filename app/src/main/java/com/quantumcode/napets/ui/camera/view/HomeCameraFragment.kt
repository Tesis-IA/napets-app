package com.quantumcode.napets.ui.camera.view

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
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
            shouldShowRequestPermission()
        }
    }

    private fun shouldShowRequestPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestCameraPermission.launch(
                    android.Manifest.permission.CAMERA
                )
            } else {
                requestCameraPermission.launch(
                    android.Manifest.permission.CAMERA
                )
            }
        } else {
            navigateToTakePictureScreen()
        }
    }

    private fun navigateToTakePictureScreen() {
        findNavController().navigate(HomeCameraFragmentDirections.actionHomeCameraFragmentToTakePictureFragment())
    }

    override fun setObservers() {
        super.setObservers()
    }

    private val requestCameraPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            navigateToTakePictureScreen()
        } else {
            Toast.makeText(requireContext(), "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }
}
