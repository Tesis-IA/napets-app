package com.quantumcode.napets.ui.camera.view

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.quantumcode.napets.databinding.FragmentTakePictureBinding
import com.quantumcode.napets.ui.base.BaseFragment

class TakePictureFragment : BaseFragment<FragmentTakePictureBinding>() {

    override var isBottomNavVisible = View.GONE

    override fun getViewBinding() = FragmentTakePictureBinding.inflate(layoutInflater)

    override fun setListeners() {
        binding.apply {
            takePictureToolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            takePictureHelp.setOnClickListener {
                HelpTakePictureDialog.newInstance().show(childFragmentManager, "HelpTakePictureDialog")
            }

            takePictureGallery.setOnClickListener {
                shouldShowRequestPermission()
            }
        }
    }

    private fun shouldShowRequestPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestImagePermission.launch(
                    android.Manifest.permission.READ_MEDIA_IMAGES
                )
            } else {
                requestImagePermission.launch(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
        } else {
            goToGallery()
        }
    }

    private val choiceImageFromGallery = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {

    }

    private val requestImagePermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            goToGallery()
        } else {
            Toast.makeText(requireContext(), "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToGallery() {
        choiceImageFromGallery.launch("image/*")
    }
}