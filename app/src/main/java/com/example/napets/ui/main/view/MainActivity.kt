package com.example.napets.ui.main.view

import android.os.Bundle
import com.example.napets.R
import com.example.napets.core.base.BaseActivity
import com.example.napets.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}