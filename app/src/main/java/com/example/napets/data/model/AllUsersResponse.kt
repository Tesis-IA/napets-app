package com.example.napets.data.model

import com.example.napets.data.domainmodel.Users

data class AllUsersResponse(
    val allUsers: List<Users> = emptyList()
)