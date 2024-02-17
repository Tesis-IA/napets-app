package com.quantumcode.napets.data.domainmodel.pestDisease

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PestDiseaseResponse(
    val id: Int?,
    val name: String?,
    val image: String?,
    val category: String?
) : Parcelable
