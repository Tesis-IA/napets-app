package com.quantumcode.napets.ui.cropsTips.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quantumcode.napets.databinding.FragmentCropsTipsBinding
import com.quantumcode.napets.ui.base.BaseFragment
import com.quantumcode.napets.ui.cropsTips.view.adapter.CropsTipsAdapter
import com.quantumcode.napets.ui.cropsTips.view.adapter.CropsTipsAdapterListener
import com.quantumcode.napets.ui.cropsTips.viewmodel.CropsTipsViewModel
import com.quantumcode.napets.ui.utils.setGone
import com.quantumcode.napets.ui.utils.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CropsTipsFragment :  BaseFragment<FragmentCropsTipsBinding>(), CropsTipsAdapterListener {

    override var isBottomNavVisible = View.GONE
    private val viewModel: CropsTipsViewModel by viewModels()
    private var cropsTipsAdapter: CropsTipsAdapter? = null
    override fun getViewBinding() = FragmentCropsTipsBinding.inflate(layoutInflater)

    override fun createView(view: View, savedInstanceState: Bundle?) {
        super.createView(view, savedInstanceState)
        setupCropsTipsAdapter()
        viewModel.getCropsTips()
    }

    override fun setObservers() {
        viewModel.cropsTips.observe(viewLifecycleOwner) { cropsTips ->
            if (cropsTips.isNotEmpty()) {
                binding.cropsTipsRv.setVisible()
                binding.cropsTipsNoData.setGone()
                cropsTipsAdapter?.setCropsTipsList(cropsTips)
            } else {
                binding.cropsTipsNoData.setVisible()
                binding.cropsTipsRv.setGone()
            }
        }
    }

    private fun setupCropsTipsAdapter() {
        cropsTipsAdapter = CropsTipsAdapter(this)
        binding.cropsTipsRv.adapter = cropsTipsAdapter
    }

    override fun setListeners() {
        binding.cropsTipsBackStack.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
