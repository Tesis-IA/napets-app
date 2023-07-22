package com.quantumcode.napets.ui.community.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quantumcode.napets.R
import com.quantumcode.napets.databinding.FragmentHomeCommunityBinding
import com.quantumcode.napets.ui.base.BaseFragment

class HomeCommunityFragment : BaseFragment<FragmentHomeCommunityBinding>() {
    override fun getViewBinding() = FragmentHomeCommunityBinding.inflate(layoutInflater)

}