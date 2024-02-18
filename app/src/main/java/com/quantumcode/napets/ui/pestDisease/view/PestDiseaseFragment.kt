package com.quantumcode.napets.ui.pestDisease.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quantumcode.napets.data.model.pestDisease.PestDisease
import com.quantumcode.napets.databinding.FragmentPestDiseaseFragmentBinding
import com.quantumcode.napets.ui.base.BaseFragment
import com.quantumcode.napets.ui.pestDisease.view.adapter.PestDiseaseAdapter
import com.quantumcode.napets.ui.pestDisease.view.adapter.PestDiseaseAdapterListener
import com.quantumcode.napets.ui.pestDisease.viewmodel.PestDiseaseViewModel
import com.quantumcode.napets.utils.setGone
import com.quantumcode.napets.utils.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PestDiseaseFragment : BaseFragment<FragmentPestDiseaseFragmentBinding>(), PestDiseaseAdapterListener {

    override var isBottomNavVisible = View.GONE
    private val viewModel: PestDiseaseViewModel by viewModels()
    private var pestDiseaseAdapter: PestDiseaseAdapter? = null
    override fun getViewBinding() = FragmentPestDiseaseFragmentBinding.inflate(layoutInflater)

    override fun createView(view: View, savedInstanceState: Bundle?) {
        super.createView(view, savedInstanceState)
        setupPestDiseaseAdapter()
        viewModel.getPestDisease()
    }

    private fun setupPestDiseaseAdapter() {
        pestDiseaseAdapter = PestDiseaseAdapter(this)
        binding.pestDiseaseRv.adapter = pestDiseaseAdapter
    }

    override fun setListeners() {
        binding.historyBackStack.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun setObservers() {
        viewModel.pestDisease.observe(viewLifecycleOwner) { pestDisease ->
            if (pestDisease.isNotEmpty()) {
                binding.pestDiseaseRv.setVisible()
                pestDiseaseAdapter?.setPestDiseaseList(pestDisease)
            } else {
                binding.fragmentPestDiseaseNoData.setVisible()
                binding.pestDiseaseRv.setGone()
            }
        }
    }

    override fun onItemClick(pestDisease: PestDisease) {
        Toast.makeText(requireContext(), "Go to details for this pest/disease", Toast.LENGTH_SHORT).show()
    }

}
