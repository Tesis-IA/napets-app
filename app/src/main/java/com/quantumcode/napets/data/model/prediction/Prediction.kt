package com.quantumcode.napets.data.model.prediction

import android.os.Parcelable
import com.quantumcode.napets.data.domainmodel.prediction.PredictionResponse
import com.quantumcode.napets.data.domainmodel.product.ProductResponse
import com.quantumcode.napets.data.model.product.Product
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Prediction(
    val id: Int,
    val name: String,
    val description: String,
    val images: List<String>,
    val warnings: List<String>,
    val moreInfo: List<String>,
    val category: String,
    val product: List<Product>
) : Parcelable {
    constructor(predictionResponse: PredictionResponse) : this(
        id = predictionResponse.id ?: 0,
        name = predictionResponse.name.orEmpty(),
        description = predictionResponse.description.orEmpty(),
        images = predictionResponse.images.orEmpty(),
        warnings = predictionResponse.warning.orEmpty(),
        moreInfo = predictionResponse.moreInfo.orEmpty(),
        category = predictionResponse.category.orEmpty(),
        product = predictionResponse.product?.map { Product(it) }.orEmpty()
    )
}
