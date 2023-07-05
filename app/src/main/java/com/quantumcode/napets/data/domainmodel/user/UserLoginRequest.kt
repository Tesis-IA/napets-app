package com.quantumcode.napets.data.domainmodel.user

data class UserLoginRequest(
    val email: String,
    val password: String
)