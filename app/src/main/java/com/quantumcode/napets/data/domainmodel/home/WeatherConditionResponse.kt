package com.quantumcode.napets.data.domainmodel.home

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class WeatherConditionResponse(
    val text: String?,
    val icon: String?
) : Parcelable