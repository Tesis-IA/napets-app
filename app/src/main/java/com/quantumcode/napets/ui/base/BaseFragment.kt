package com.quantumcode.napets.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.quantumcode.napets.ui.main.view.MainActivity

abstract class BaseFragment<B: ViewBinding> : Fragment() {

    lateinit var binding: B
    protected open var isBottomNavVisible = View.VISIBLE

    protected abstract fun getViewBinding(): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomNavigationVisibility()
        return binding.root
    }

    private fun bottomNavigationVisibility() {
        // get the reference of the parent activity and call the setBottomNavigationVisibility method.
        (activity as MainActivity).isBottomNavVisible(isBottomNavVisible)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).isBottomNavVisible(isBottomNavVisible)
    }

    private fun init() {
        binding = getViewBinding()
    }
}
