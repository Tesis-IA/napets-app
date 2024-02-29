package com.quantumcode.napets.data.model.home

import android.os.Parcelable
import com.quantumcode.napets.data.domainmodel.home.WeatherConditionResponse
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class WeatherCondition(
    val text: String,
    val icon: String
) : Parcelable {
    constructor(weatherConditionResponse: WeatherConditionResponse?) : this(
        text = weatherConditionResponse?.text.orEmpty(),
        icon = weatherConditionResponse?.icon.orEmpty()
    )
}
