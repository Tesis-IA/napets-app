package com.example.napets.data.domainmodel

import android.os.Parcelable
import com.example.napets.data.model.UserData
import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserResponse(
    val accessToken: String,
    val user: UserData
) : Parcelable
