package com.quantumcode.napets.data.domainmodel.home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@JsonClass(generateAdapter = true)
data class CurrentWeatherResponse(
    @SerializedName("temp_c") val celsiusDegrees: Float?,
    @SerializedName("temp_f") val fahrenheitDegrees: Float?,
    @SerializedName("last_updated_epoch") val lastUpdateEpoch: Long?,
    @SerializedName("last_update") val lastUpdate: Date?,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("wind_mph") val windMph: Float?,
    @SerializedName("wind_kph") val windKph: Float?,
    @SerializedName("wind_degree") val windDegree: Int?,
    @SerializedName("wind_dir") val windDir: String?,
    @SerializedName("pressure_mb") val pressureMb: Int?,
    @SerializedName("pressure_in") val pressureIn: Float?,
    @SerializedName("precip_mm") val precipMm: Int?,
    @SerializedName("precip_in") val precipIn: Int?,
    @SerializedName("feelslike_c") val feelslikeC: Float?,
    @SerializedName("feelslike_f") val feelslikeF: Float?,
    @SerializedName("vis_km") val visKm: Int?,
    @SerializedName("vis_miles") val visMiles: Int?,
    @SerializedName("gust_mph") val  gustMph: Float?,
    @SerializedName("gust_kph") val gustKph: Float?,
    @SerializedName("uv") val ultraViolet: Int?,
    val condition: WeatherConditionResponse?,
    val humidity: Int?,
    val cloud: Int?
) : Parcelable
