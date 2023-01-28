package com.example.napets.data.repository

import android.util.Log
import com.example.napets.core.ApiService
import com.example.napets.data.model.AllUsersResponse
import com.example.napets.data.model.UserResponse
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : IAuthenticationRepository{
    override suspend fun userLogin(password: String, email: String): Boolean {
        TODO("Not yet implemented")
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
        Log.i("allUsers", users.body().toString())
    }
}