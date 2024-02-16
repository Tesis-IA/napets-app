package com.quantumcode.napets.data.domainmodel.user

import com.google.gson.annotations.SerializedName

data class UserRequest(
    val username: String,
    val email: String,
    val password: String,
    @SerializedName("auth_strategy") val authStrategy: String,
    @SerializedName("device_id") val deviceId: String
)
