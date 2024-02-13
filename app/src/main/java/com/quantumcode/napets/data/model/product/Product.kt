package com.quantumcode.napets.data.model.product

import com.google.gson.annotations.SerializedName
import com.quantumcode.napets.data.domainmodel.product.ProductResponse

data class Product(
    val id: Int,
    val category: String,
    val image: String,
    val name: String,
    val whereFounded: String
) {
    constructor(productResponse: ProductResponse) : this(
        id = productResponse.id ?: 0,
        category = productResponse.category.orEmpty(),
        image = productResponse.image.orEmpty(),
        name = productResponse.name.orEmpty(),
        whereFounded = productResponse.whereFounded.orEmpty()
    )
}
