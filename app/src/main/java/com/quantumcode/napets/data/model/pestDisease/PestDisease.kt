package com.quantumcode.napets.data.model.pestDisease

import android.os.Parcelable
import com.quantumcode.napets.data.domainmodel.pestDisease.PestDiseaseResponse
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PestDisease(
    val id: Int,
    val name: String,
    val image: String,
    val category: String
) : Parcelable {
    constructor(pestDiseaseResponse: PestDiseaseResponse) : this(
        id = pestDiseaseResponse.id ?: 0,
        name = pestDiseaseResponse.name.orEmpty(),
        image = pestDiseaseResponse.image.orEmpty(),
        category = pestDiseaseResponse.category.orEmpty()
    )
}
