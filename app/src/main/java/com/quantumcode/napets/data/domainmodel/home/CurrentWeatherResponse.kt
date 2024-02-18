package com.quantumcode.napets.data.domainmodel.home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CurrentWeatherResponse(
    @SerializedName("temp_c") val celsiusDegrees: Float?,
    @SerializedName("temp_f") val fahrenheitDegrees: Float?,
    val condition: WeatherConditionResponse?,
    val humidity: Int?,
    val cloud: Int?
) : Parcelable
