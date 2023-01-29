package com.example.napets.data.domainmodel

data class Users(
    val username: String,
    val name: String,
    val lastname: String,
    val email: String,
    val password: String,
    val city: String,
    val province: String,
    val role: String
)