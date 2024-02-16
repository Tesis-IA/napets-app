package com.quantumcode.napets.ui.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import com.quantumcode.napets.R
import com.quantumcode.napets.ui.extension.setupWithNavController
import com.quantumcode.napets.ui.base.BaseActivity
import com.quantumcode.napets.databinding.ActivityMainBinding
import com.quantumcode.napets.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var toggle: ActionBarDrawerToggle
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
        setupDrawerLayout()
        setObservers()
        setListeners()
        setDeviceId()
        viewModel.isAuthored()
    }

    @SuppressLint("HardwareIds")
    private fun setDeviceId() {
        val deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        viewModel.setDeviceId(deviceId)
    }

    private fun setListeners() {
        binding.mainDrawerButton.setOnClickListener {
            binding.root.openDrawer(binding.mainNavigationView, true)
        }
    }

    private fun setupDrawerLayout() {
        binding.mainDrawerButton.bringToFront()
        toggle = ActionBarDrawerToggle(this, binding.root, R.string.close, R.string.open)
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
        binding.apply {
            mainBottomNavigation.visibility = visibility
            mainDrawerButton.visibility = visibility
            mainTextHeader.visibility = visibility
            mainBottomNavigation.visibility = visibility
            if (visibility == 8) {
                root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            } else {
                root.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.navigateUp() ?: false
    }
}
