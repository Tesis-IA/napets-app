package com.quantumcode.napets.data.repository

import com.quantumcode.napets.core.ApiService
import com.quantumcode.napets.data.domainmodel.UserResponse
import com.quantumcode.napets.data.model.auth.UserLogin
import retrofit2.Response
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : IAuthenticationRepository{
    override suspend fun userLogin(email: String, password: String): Response<UserResponse> {
        return apiService.login(
            UserLogin(
                username = email,
                password = password
            )
        )
    }


    override suspend fun createAccount(
        password: String,
        email: String,
        username: String
    ): UserResponse {
        TODO("Not yet implemented")
    }
}