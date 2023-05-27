package com.quantumcode.napets.data.model.auth

import android.os.Parcelable
import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserData(
    val id: String,
    val username: String,
    val email: String,
    val password: String
) : Parcelable