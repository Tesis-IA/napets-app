package com.quantumcode.napets.data.repository

import com.quantumcode.napets.data.domainmodel.UserResponse
import retrofit2.Response

interface IAuthenticationRepository {
    suspend fun userLogin(email: String, password: String): Response<UserResponse>

    suspend fun createAccount(password: String, email: String, username: String): UserResponse
}