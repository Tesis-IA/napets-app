package com.quantumcode.napets.data.model.history

import android.os.Parcelable
import com.quantumcode.napets.data.domainmodel.history.HistoryResponse
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@JsonClass(generateAdapter = true)
data class History(
    val id: Int,
    val image: List<String>,
    val diagnostic: String,
    val createdAt: Date
) : Parcelable {
    constructor(historyResponse: HistoryResponse) : this (
        id = historyResponse.id ?: 0,
        image = historyResponse.image.orEmpty(),
        diagnostic = historyResponse.diagnostic.orEmpty(),
        createdAt = historyResponse.createdAt ?: Date()
    )
}
