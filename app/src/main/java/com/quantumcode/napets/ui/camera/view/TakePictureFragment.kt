package com.quantumcode.napets.ui.camera.view

import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.AspectRatioStrategy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quantumcode.napets.databinding.FragmentTakePictureBinding
import com.quantumcode.napets.ui.base.BaseFragment
import com.quantumcode.napets.ui.camera.viewmodel.CameraViewModel
import com.quantumcode.napets.ui.main.viewmodel.MainViewModel
import com.quantumcode.napets.utils.removeProgress
import com.quantumcode.napets.utils.setGone
import com.quantumcode.napets.utils.setInvisible
import com.quantumcode.napets.utils.setShowProgress
import com.quantumcode.napets.utils.setVisible
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.Calendar
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class TakePictureFragment : BaseFragment<FragmentTakePictureBinding>() {

    private val viewModel: CameraViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override var isBottomNavVisible = View.GONE
    private var imageCapture: ImageCapture? = null
    private var cameraExecutor: ExecutorService? = null
    private var bitmapBuffer: Bitmap? = null
    private var imageRotationDegrees = 0
    private var pauseAnalysis = false
    private var nameImageCapture = ""

    override fun getViewBinding() = FragmentTakePictureBinding.inflate(layoutInflater)

    override fun createView(view: View, savedInstanceState: Bundle?) {
        super.createView(view, savedInstanceState)
        cameraExecutor =  Executors.newSingleThreadExecutor()
        initializeCamera()
    }

    private fun initializeCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider = cameraProviderFuture.get()

            //Preview
            val preview = Preview.Builder()
                .setResolutionSelector(
                    ResolutionSelector.Builder()
                        .setAspectRatioStrategy(
                            AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY
                        )
                        .setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY)
                        .build()
                )
                .setTargetRotation(binding.takePictureCameraPreview.display.rotation)
                .build()
                .also {
                    it.setSurfaceProvider(binding.takePictureCameraPreview.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            val imageAnalyzer = ImageAnalysis.Builder()
                .setResolutionSelector(
                    ResolutionSelector.Builder()
                        .setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY)
                        .setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY)
                        .build()
                )
                .setTargetRotation(binding.takePictureCameraPreview.display.rotation)
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                .build()
                .also {
                    it.setAnalyzer(
                        cameraExecutor ?: return@also
                    ) { image ->
                        Log.d("Take Picture", "Average luminosity: $image")
                    }
                }

            imageAnalyzer.setAnalyzer(cameraExecutor ?: return@addListener) { image ->
                if (bitmapBuffer == null) {
                    imageRotationDegrees = image.imageInfo.rotationDegrees
                    bitmapBuffer = Bitmap.createBitmap(
                        image.width, image.height, Bitmap.Config.ARGB_8888
                    )
                    if (pauseAnalysis) {
                        image.close()
                        return@setAnalyzer
                    }
                    image.use { bitmapBuffer?.copyPixelsFromBuffer(image.planes[0].buffer) }
                }
            }

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture, imageAnalyzer
                )
            } catch (exc: Exception) {
                Log.e("Take Picture", "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun saveCapturedPhoto() {

        // Create time stamped name and MediaStore entry.
        nameImageCapture = "${Calendar.DAY_OF_WEEK_IN_MONTH}${Calendar.MONTH}${Calendar.YEAR}${Calendar.HOUR}${Calendar.SECOND}${Calendar.MILLISECOND}"
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, nameImageCapture)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                activity?.contentResolver ?: return,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture?.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e("Take Picture", "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    binding.apply {
                        takePictureButton.setShowProgress(false, null)
                        takePictureButton.setInvisible()
                        takePictureSendPhotoButton.setVisible()
                    }
                }
            }
        )
    }

    private fun makePrediction() {
        val path = "${Environment.getExternalStorageDirectory().absolutePath}/Pictures/CameraX-Image/${nameImageCapture}.jpg"
        val file = File(path)
        if (file.exists()) {
            viewModel.makePrediction(
                mainViewModel.getDeviceId(),
                file
            )
        } else {
            Toast.makeText(requireContext(), "File not found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setListeners() {
        binding.apply {
            takePictureBackStack.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            takePictureToolbarClosePreview.setNavigationOnClickListener {
                onOutsidePreview()
            }

            takePictureHelp.setOnClickListener {
                HelpTakePictureDialog.newInstance()
                    .show(childFragmentManager, "HelpTakePictureDialog")
            }

            takePictureGallery.setOnClickListener {
                shouldShowRequestPermission()
            }

            takePictureButton.setOnClickListener {
                takePhoto()
            }

            takePictureSendPhotoButton.setOnClickListener {
                makePrediction()
            }
        }
    }

    override fun setObservers() {
        viewModel.errorResponse.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        viewModel.prediction.observe(viewLifecycleOwner) { response ->
            Log.d("Take Picture", response.toString())
            findNavController().navigate(TakePictureFragmentDirections.actionTakePictureFragmentToDiagnosisFragment(response))
        }
    }

    private fun takePhoto() {
        binding.takePictureButton.apply {
            setShowProgress(true, null)
            if(bitmapBuffer != null){
                saveCapturedPhoto()
                isEnabled = false
                if (!pauseAnalysis) {
                    pauseAnalysis = true
                    onCreatePreview()
                }
                isEnabled = true
            }
        }
    }

    private fun onOutsidePreview() {
        pauseAnalysis = false
        initializeCamera()
        with(binding) {
            takePictureImagePreview.setImageBitmap(null)
            takePictureButton.setVisible()
            takePictureSendPhotoButton.setGone()
            takePictureButton.removeProgress()
            takePictureToolbarClosePreview.setGone()
            takePictureBackStack.setVisible()
            takePictureGallery.setVisible()
            takePictureIndicator.setVisible()
            takePictureHelp.setVisible()
            takePictureCameraPreview.setVisible()
        }
    }

    private fun onCreatePreview() {
        val matrix = Matrix().apply {
            postRotate(imageRotationDegrees.toFloat())
        }
        val uprightImage = Bitmap.createBitmap(
            bitmapBuffer ?: return, 0, 0, bitmapBuffer?.width ?: return, bitmapBuffer?.height ?: return, matrix, true
        )
        binding.takePictureImagePreview.setImageBitmap(uprightImage)
        hideViewInPreview()
    }

    private fun hideViewInPreview() {
        with(binding) {
            takePictureToolbarClosePreview.setVisible()
            takePictureBackStack.setGone()
            takePictureIndicator.setGone()
            takePictureGallery.setGone()
            takePictureHelp.setGone()
            takePictureCameraPreview.setGone()
        }
    }

    private fun shouldShowRequestPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_DENIED
        ) {
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
        if (it != null) {
            binding.takePictureImagePreview.setImageURI(it)
            binding.apply {
                takePictureButton.setShowProgress(false, null)
                takePictureButton.setInvisible()
                takePictureSendPhotoButton.setVisible()
            }
            hideViewInPreview()
        }
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

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor?.apply {
            shutdown()
            awaitTermination(1000, TimeUnit.MILLISECONDS)
        }
    }
}
