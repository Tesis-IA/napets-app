package com.example.napets.data.repository

import com.example.napets.data.domainmodel.UserResponse

interface IAuthenticationRepository {
    suspend fun userLogin(password: String, email: String): Boolean

    suspend fun createAccount(password: String, email: String, username: String): UserResponse

    suspend fun getUsers()
}