package com.quantumcode.napets.data.model.history

import com.quantumcode.napets.data.domainmodel.history.HistoryResponse
import java.util.Date

data class History(
    val id: Int,
    val image: List<String>,
    val diagnostic: String,
    val createdAt: Date
) {
    constructor(historyResponse: HistoryResponse) : this (
        id = historyResponse.id ?: 0,
        image = historyResponse.image.orEmpty(),
        diagnostic = historyResponse.diagnostic.orEmpty(),
        createdAt = historyResponse.createdAt ?: Date()
    )
}
