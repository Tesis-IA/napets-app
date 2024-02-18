package com.quantumcode.napets.data.model.home

import android.os.Parcelable
import com.quantumcode.napets.data.domainmodel.home.WeatherResponse
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Weather(
    val location: Location,
    val currentWeather: CurrentWeather
) : Parcelable {
    constructor(weatherResponse: WeatherResponse) : this(
        location = Location(weatherResponse.location),
        currentWeather = CurrentWeather(weatherResponse.current)
    )
}