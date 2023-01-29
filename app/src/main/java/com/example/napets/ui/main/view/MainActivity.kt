package com.example.napets.ui.main.view

import android.opengl.Visibility
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.napets.R
import com.example.napets.data.extension.setupWithNavController
import com.example.napets.ui.base.BaseActivity
import com.example.napets.databinding.ActivityMainBinding
import com.example.napets.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    private var currentNavController: NavController? = null
    private val navGraphIds = listOf(
        R.navigation.nav_graph_home,
        R.navigation.nav_graph_login
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUsers()
        if(savedInstanceState == null) setupBottomNavigation()
        setObservers()
    }

    private fun setObservers() {
        navigateToLogin()
    }

    private fun setupBottomNavigation() {
        val controller = binding.mainBottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.main_fragment_container,
            intent = intent
        )

        currentNavController = controller.value
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigation()
    }

    fun navigateToLogin(){
        currentNavController?.navigate(R.id.action_landingFragment_to_nav_graph_login)
    }

    fun isBottomNavVisible(visibility: Int){
        binding.mainBottomNavigation.visibility = visibility
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.navigateUp() ?: false
    }
}