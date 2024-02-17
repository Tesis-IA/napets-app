package com.quantumcode.napets.data.domainmodel.prediction

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.quantumcode.napets.data.domainmodel.product.ProductResponse
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PredictionResponse (
    val id: Int?,
    val name: String?,
    val description: String?,
    val images: List<String>?,
    val warnings: List<String>?,
    @SerializedName("more_info") val moreInfo: List<String>?,
    val category: String?,
    val product: List<ProductResponse>?
) : Parcelable
