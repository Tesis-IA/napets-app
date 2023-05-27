package com.quantumcode.napets.data.domainmodel

import android.os.Parcelable
import com.quantumcode.napets.data.model.auth.UserData
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserResponse(
    val accessToken: String,
    val user: UserData
) : Parcelable
