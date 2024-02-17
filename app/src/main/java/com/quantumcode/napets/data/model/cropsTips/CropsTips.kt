package com.quantumcode.napets.data.model.cropsTips

import android.os.Parcelable
import com.quantumcode.napets.data.domainmodel.cropsTips.CropsTipsResponse
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CropsTips(
    val id: Int,
    val name: String,
    val image: String,
    val background: String,
    val shapeBackground: String
) : Parcelable {
    constructor(cropsTipsResponse: CropsTipsResponse) : this(
        id = cropsTipsResponse.id ?: 0,
        name = cropsTipsResponse.name.orEmpty(),
        image = cropsTipsResponse.image.orEmpty(),
        background = cropsTipsResponse.background ?: "5C941A",
        shapeBackground = cropsTipsResponse.shapeBackground ?: "5C941A"
    )
}
