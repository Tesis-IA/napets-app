package com.example.napets.data.domainmodel

import android.os.Parcelable
import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ErrorResponse(val message: String? = null): Parcelable
