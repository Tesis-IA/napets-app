package com.quantumcode.napets.data.domainmodel.cropsTips

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CropsTipsResponse(
    val id: Int?,
    val name: String?,
    val image: String?,
    val background: String?,
    @SerializedName("shape_background") val shapeBackground: String?
) : Parcelable