package com.quantumcode.napets.data.domainmodel.user

data class UserRequest(
    val username: String,
    val email: String,
    val password: String,
    val authStrategy: String
)
