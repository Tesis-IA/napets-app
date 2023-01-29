package com.example.napets.data.domainmodel

import com.example.napets.data.model.UserData

data class UsersResponse(
    val allUsers: List<UserData> = emptyList()
)