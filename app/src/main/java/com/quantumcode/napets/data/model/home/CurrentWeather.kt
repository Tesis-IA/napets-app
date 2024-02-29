package com.quantumcode.napets.data.model.home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.quantumcode.napets.data.domainmodel.home.CurrentWeatherResponse
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CurrentWeather(
    @SerializedName("temp_c") val celsiusDegrees: Float,
    @SerializedName("temp_f") val fahrenheitDegrees: Float,
    val condition: WeatherCondition,
    val humidity: Int,
    val cloud: Int
) : Parcelable {
    constructor(currentWeatherResponse: CurrentWeatherResponse?) : this(
        celsiusDegrees = currentWeatherResponse?.celsiusDegrees ?: 0F,
        fahrenheitDegrees = currentWeatherResponse?.fahrenheitDegrees ?: 0F,
        condition = WeatherCondition(currentWeatherResponse?.condition),
        humidity = currentWeatherResponse?.humidity ?: 0,
        cloud = currentWeatherResponse?.cloud ?: 0
    )
}
