package com.example.napets.data.domainmodel

import com.example.napets.data.model.UserData

data class UserResponse(
    val accessToken: String,
    val user: UserData
)
