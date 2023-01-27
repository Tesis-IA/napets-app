package com.example.napets.ui.main.view

import android.opengl.Visibility
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.napets.R
import com.example.napets.data.extension.setupWithNavController
import com.example.napets.ui.base.BaseActivity
import com.example.napets.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private var currentNavController: LiveData<NavController>? = null
    private val navGraphIds = listOf(
        R.navigation.nav_graph_home
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val controller = binding.mainBottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.main_fragment_container,
            intent = intent
        )

        currentNavController = controller
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigation()
    }

    fun isBottomNavVisible(visibility: Int){
        binding.mainBottomNavigation.visibility = visibility
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}