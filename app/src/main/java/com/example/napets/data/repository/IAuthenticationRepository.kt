package com.example.napets.data.repository

import com.android.volley.NetworkResponse
import com.example.napets.data.model.UserResponse

interface IAuthenticationRepository {
    suspend fun userLogin(password: String, email: String): Boolean

    suspend fun createAccount(password: String, email: String, username: String): UserResponse
}