package com.example.napets.data.repository

import com.example.napets.data.domainmodel.ErrorResponse
import com.example.napets.data.domainmodel.UserResponse
import com.haroldadmin.cnradapter.NetworkResponse

interface IAuthenticationRepository {
    suspend fun userLogin(email: String, password: String): NetworkResponse<UserResponse, ErrorResponse>

    suspend fun createAccount(password: String, email: String, username: String): UserResponse

    suspend fun getUsers()
}