package com.quantumcode.napets.ui.home.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.quantumcode.napets.R
import com.quantumcode.napets.data.model.home.Subject
import com.quantumcode.napets.data.model.home.Weather
import com.quantumcode.napets.databinding.FragmentHomeBinding
import com.quantumcode.napets.ui.base.BaseFragment
import com.quantumcode.napets.ui.home.adapter.HomeSubjectAdapterListener
import com.quantumcode.napets.ui.home.adapter.HomeSubjectsAdapter
import com.quantumcode.napets.ui.home.viewmodel.HomeViewModel
import com.quantumcode.napets.ui.main.viewmodel.MainViewModel
import com.quantumcode.napets.utils.getFormattedDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeSubjectAdapterListener {

    override var isBottomNavVisible = View.VISIBLE
    private val viewModel: HomeViewModel by viewModels()
    private var subjectsAdapter: HomeSubjectsAdapter? = null

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun createView(view: View, savedInstanceState: Bundle?) {
        super.createView(view, savedInstanceState)
        setupRecyclers()
        getWeather()
        viewModel.getSubjects()
    }

    private fun getWeather() {
        viewModel.getCurrentWeather(
            latLong = "12.4317836,-86.8922713",
            language = "es",
            requestType = "current.json"
        )
    }

    override fun setObservers() {
        viewModel.subject.observe(viewLifecycleOwner) { response ->
            subjectsAdapter?.submitList(response)
        }

        viewModel.weather.observe(viewLifecycleOwner) { weather ->
            if (weather == null) return@observe
            setWeatherUI(weather)
        }
    }

    private fun setWeatherUI(weather: Weather) {
        with(binding) {
            val currentDate = weather.location.localtime.getFormattedDate("dd MMM")
            homeWeatherCurrentDate.text =
                resources.getString(R.string.current_formatted_date, currentDate)

            homeWeatherState.text = resources.getString(
                R.string.celsius_degrees,
                weather.currentWeather.celsiusDegrees.toString()
            )

            val hour = weather.location.localtime.getFormattedDate("HH:mm")
            homeWeatherDescription.text = resources.getString(
                R.string.weather_description,
                weather.currentWeather.condition.text,
                hour
            )

            homeWeatherCurrentLocation.text = resources.getString(
                R.string.current_location,
                weather.location.region,
                weather.location.country
            )
            homeWeatherHumidity.text = resources.getString(
                R.string.weather_humidity,
                weather.currentWeather.humidity.toString() + "%"
            )

            Glide.with(root.context)
                .load("https://${weather.currentWeather.condition.icon}")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_my_crops)
                .into(homeWeatherImage)
        }
    }

    private fun setupRecyclers() {
        subjectsAdapter = HomeSubjectsAdapter(this)
        binding.homeSubjectRv.adapter = subjectsAdapter
    }

    override fun onSelectedItem(subject: Subject) {
        when (subject.id) {
            1 -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToHistoryFragment())
            2 -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPestDiseaseFragment())
            3 -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCropsTipsFragment())
        }
    }
}
