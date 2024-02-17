package com.quantumcode.napets.data.domainmodel.user

import com.google.gson.annotations.SerializedName

data class GuestRequest(
    @SerializedName("device_id") val deviceId: String
)

