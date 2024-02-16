package com.quantumcode.napets.data.domainmodel.user

import com.google.gson.annotations.SerializedName

data class UserLoginRequest(
    val email: String,
    val password: String,
    @SerializedName("device_id") val deviceId: String
)
