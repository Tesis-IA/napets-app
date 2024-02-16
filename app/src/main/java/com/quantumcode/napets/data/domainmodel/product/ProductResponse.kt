package com.quantumcode.napets.data.domainmodel.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductResponse(
    val id: Int?,
    val category: String?,
    val image: String?,
    val name: String?,
    @SerializedName("where_founded") val whereFounded: String?
): Parcelable
