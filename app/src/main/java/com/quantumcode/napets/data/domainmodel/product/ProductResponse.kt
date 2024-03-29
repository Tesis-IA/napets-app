package com.quantumcode.napets.data.domainmodel.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ProductResponse(
    val id: Int?,
    val category: String?,
    val image: String?,
    val name: String?,
    @SerializedName("where_founded") val whereFounded: String?
): Parcelable
