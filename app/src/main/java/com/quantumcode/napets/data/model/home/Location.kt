package com.quantumcode.napets.data.model.home

import android.os.Parcelable
import com.quantumcode.napets.data.domainmodel.home.LocationResponse
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@JsonClass(generateAdapter = true)
data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtime: Date
) : Parcelable {
    constructor(locationResponse: LocationResponse?) : this(
        name = locationResponse?.name.orEmpty(),
        region = locationResponse?.region.orEmpty(),
        country = locationResponse?.country.orEmpty(),
        localtime = locationResponse?.localtime ?: Date()
    )
}
