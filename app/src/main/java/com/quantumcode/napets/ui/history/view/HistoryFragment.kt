package com.quantumcode.napets.ui.history.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.quantumcode.napets.data.model.history.History
import com.quantumcode.napets.databinding.FragmentHistoryBinding
import com.quantumcode.napets.ui.base.BaseFragment
import com.quantumcode.napets.ui.history.view.adapter.HistoryAdapter
import com.quantumcode.napets.ui.history.viewmodel.HistoryViewModel
import com.quantumcode.napets.ui.main.viewmodel.MainViewModel
import com.quantumcode.napets.ui.utils.setGone
import com.quantumcode.napets.ui.utils.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    override var isBottomNavVisible = View.GONE
    private val viewModel: HistoryViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private var historyAdapter: HistoryAdapter? = null

    override fun getViewBinding() = FragmentHistoryBinding.inflate(layoutInflater)

    override fun createView(view: View, savedInstanceState: Bundle?) {
        super.createView(view, savedInstanceState)
        setupHistoryAdapter()
        viewModel.getHistoryByUserId(mainViewModel.getDeviceId())
    }

    override fun setListeners() {
        binding.historyBackStack.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun setObservers() {
        viewModel.history.observe(viewLifecycleOwner) { history ->
            if (history.isNotEmpty()) {
                binding.fragmentHistoryNoData.setGone()
                binding.historyRv.setVisible()
                historyAdapter?.setHistoryList(history)
            } else {
                binding.historyRv.setGone()
                binding.fragmentHistoryNoData.setVisible()
            }
        }
    }
    private fun setupHistoryAdapter() {
        historyAdapter = HistoryAdapter()
        binding.historyRv.adapter = historyAdapter
    }
}
