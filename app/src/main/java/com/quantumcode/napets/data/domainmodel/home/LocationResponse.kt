package com.quantumcode.napets.data.domainmodel.home

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@JsonClass(generateAdapter = true)
data class LocationResponse(
    val name: String?,
    val region: String?,
    val country: String?,
    val localtime: Date?
) : Parcelable