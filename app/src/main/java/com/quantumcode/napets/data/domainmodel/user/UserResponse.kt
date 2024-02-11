package com.quantumcode.napets.data.domainmodel.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.quantumcode.napets.data.model.auth.UserData
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserResponse(
    val id: Int?,
    val username: String?,
    val email: String?,
    val password: String?,
    val token: String?,
    @SerializedName("auth_strategy")
    val authStrategy: String?,
    @SerializedName("device_id")
    val deviceId: String?
) : Parcelable
