package com.quantumcode.napets.ui.diagnosis.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.quantumcode.napets.databinding.FragmentDiagnosisBinding
import com.quantumcode.napets.ui.base.BaseFragment
import com.quantumcode.napets.ui.diagnosis.adapter.DiagnosisInfoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class DiagnosisFragment : BaseFragment<FragmentDiagnosisBinding>() {

    override var isBottomNavVisible = View.GONE
    private val diagnosisArs: DiagnosisFragmentArgs by navArgs()
    private var diagnosisInfoAdapter: DiagnosisInfoAdapter? = null
    private var diagnosisImageAdapter: DiagnosisImageAdapter? = null
    private var diagnosisMoreInfoAdapter: DiagnosisInfoAdapter? = null
    override fun getViewBinding() = FragmentDiagnosisBinding.inflate(layoutInflater)

    override fun createView(view: View, savedInstanceState: Bundle?) {
        super.createView(view, savedInstanceState)
        setCompositeTransformer()
        setupDiagnosisAdapter()
        setUI()
    }

    private fun setUI() {
        binding.apply {
            diagnosisName.text = diagnosisArs.diagnosis.name
            diagnosisPestCategory.text = diagnosisArs.diagnosis.category
            diagnosisDescription.text = diagnosisArs.diagnosis.description
        }
    }

    private fun setupDiagnosisAdapter() {
        diagnosisImageAdapter = DiagnosisImageAdapter(diagnosisArs.diagnosis.images)
        diagnosisInfoAdapter = DiagnosisInfoAdapter(diagnosisArs.diagnosis.warnings)
        diagnosisMoreInfoAdapter = DiagnosisInfoAdapter(diagnosisArs.diagnosis.moreInfo)

        binding.apply {
            diagnosisMoreInfoRv.adapter = diagnosisMoreInfoAdapter
            diagnosisWarningRv.adapter = diagnosisInfoAdapter
            setupViewPagerImage()
        }
    }

    private fun setupViewPagerImage() {
        binding.diagnosisViewPagerImages.apply {
            adapter = diagnosisImageAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    private fun setCompositeTransformer() {
        val compositeTransformer = CompositePageTransformer()
        compositeTransformer.addTransformer(MarginPageTransformer(40))
        compositeTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.85f + r * 0.15f)
        }
        binding.diagnosisViewPagerImages.setPageTransformer(compositeTransformer)
    }

}