package com.example.napets.core

import com.example.napets.data.model.NewUserData
import com.example.napets.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("")
    suspend fun createAccount(
        @Body userData: NewUserData
    ) : Response<UserResponse>
}