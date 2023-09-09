package com.quantumcode.napets.ui.home.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.quantumcode.napets.databinding.FragmentHomeBinding
import com.quantumcode.napets.ui.base.BaseFragment
import com.quantumcode.napets.ui.home.adapter.HomeSubjectsAdapter
import com.quantumcode.napets.ui.home.viewmodel.HomeViewModel
import com.quantumcode.napets.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override var isBottomNavVisible = View.VISIBLE
    private val viewModel: HomeViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private var subjectsAdapter: HomeSubjectsAdapter? = null

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclers()
        viewModel.getSubjects()
    }

    override fun setObservers() {
        viewModel.subject.observe(viewLifecycleOwner) { response ->
            subjectsAdapter?.submitList(response)
        }
    }

    private fun setupRecyclers() {
        subjectsAdapter = HomeSubjectsAdapter()
        binding.homeSubjectRv.adapter = subjectsAdapter
    }
}
