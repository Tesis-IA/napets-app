package com.example.napets.ui.main.view

import android.os.Bundle
import com.example.napets.R
import com.example.napets.ui.base.BaseActivity
import com.example.napets.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}