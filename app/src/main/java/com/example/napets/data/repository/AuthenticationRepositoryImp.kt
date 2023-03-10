package com.example.napets.data.repository

import android.util.Log
import com.example.napets.core.ApiService
import com.example.napets.data.domainmodel.ErrorResponse
import com.example.napets.data.domainmodel.UserResponse
import com.example.napets.data.model.UserLogin
import com.haroldadmin.cnradapter.NetworkResponse
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

    override suspend fun getUsers() {
        val users = apiService.getUsers()
    }
}