package com.quantumcode.napets.ui.main.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
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
        R.navigation.nav_home,
        R.navigation.nav_login,
        R.navigation.nav_camera,
        R.navigation.nav_my_profile
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) setupBottomNavigation()
        setObservers()
        viewModel.isAuthored()
    }

    private fun setObservers() {
        viewModel.isAuthored.observe(this) {
            if(!it) {
                navigateToLogin()
            } else {
                navigateToHome()
            }
        }
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

    private fun navigateToLogin(){
        currentNavController?.setGraph(R.navigation.nav_login)
    }

    fun navigateToHome() {
        currentNavController?.setGraph(R.navigation.nav_home)
    }

    fun isBottomNavVisible(visibility: Int){
        binding.mainBottomNavigation.visibility = visibility
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.navigateUp() ?: false
    }
}
