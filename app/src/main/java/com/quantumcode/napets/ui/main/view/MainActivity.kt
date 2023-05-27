package com.quantumcode.napets.ui.main.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.quantumcode.napets.R
import com.quantumcode.napets.data.extension.setupWithNavController
import com.quantumcode.napets.ui.base.BaseActivity
import com.quantumcode.napets.databinding.ActivityMainBinding
import com.quantumcode.napets.ui.main.viewmodel.MainViewModel
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
        currentNavController?.navigate(R.id.action_homeFragment_to_nav_graph_login)
    }

    fun navigateToHome() {

    }

    fun isBottomNavVisible(visibility: Int){
        binding.mainBottomNavigation.visibility = visibility
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.navigateUp() ?: false
    }
}