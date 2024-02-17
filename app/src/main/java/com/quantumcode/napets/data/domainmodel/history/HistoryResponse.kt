package com.quantumcode.napets.data.domainmodel.history

import com.google.gson.annotations.SerializedName
import java.util.Date

data class HistoryResponse(
    val id: Int?,
    val image: List<String>?,
    val diagnostic: String?,
    @SerializedName("created_at") val createdAt: Date?
)
