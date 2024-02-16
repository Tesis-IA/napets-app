package com.quantumcode.napets.data.model.prediction

import com.quantumcode.napets.data.domainmodel.prediction.PredictionResponse
import com.quantumcode.napets.data.domainmodel.product.ProductResponse

data class Prediction(
    val id: Int,
    val name: String,
    val description: String,
    val images: List<String>,
    val warnings: List<String>,
    val moreInfo: List<String>,
    val category: String,
    val product: List<ProductResponse>
) {
    constructor(predictionResponse: PredictionResponse) : this(
        id = predictionResponse.id ?: 0,
        name = predictionResponse.name.orEmpty(),
        description = predictionResponse.description.orEmpty(),
        images = predictionResponse.images.orEmpty(),
        warnings = predictionResponse.warnings.orEmpty(),
        moreInfo = predictionResponse.moreInfo.orEmpty(),
        category = predictionResponse.category.orEmpty(),
        product = predictionResponse.product.orEmpty()
    )
}
