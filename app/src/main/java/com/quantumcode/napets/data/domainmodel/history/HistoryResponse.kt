package com.quantumcode.napets.data.domainmodel.history

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@JsonClass(generateAdapter = true)
data class HistoryResponse(
    val id: Int?,
    val image: List<String>?,
    val diagnostic: String?,
    @SerializedName("created_at") val createdAt: Date?
) : Parcelable
