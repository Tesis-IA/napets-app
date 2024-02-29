package com.quantumcode.napets.data.domainmodel.home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@JsonClass(generateAdapter = true)
data class LocationResponse(
    val name: String?,
    val region: String?,
    val country: String?,
    @SerializedName("lat") val latitude: Float?,
    @SerializedName("lon") val longitude: Float?,
    @SerializedName("tz_id") val timeZoneId: String?,
    val localtime: String?
) : Parcelable
