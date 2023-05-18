package com.quantumcode.napets.data.model

import android.os.Parcelable
import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserLogin(val username: String, val password: String): Parcelable