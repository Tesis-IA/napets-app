package com.example.napets.core

import com.example.napets.data.domainmodel.ErrorResponse
import com.example.napets.data.domainmodel.UsersResponse
import com.example.napets.data.domainmodel.UserResponse
import com.example.napets.data.model.UserData
import com.example.napets.data.model.UserLogin
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body userLogin: UserLogin
    ): NetworkResponse<UserResponse, ErrorResponse>

    @POST("createUser")
    suspend fun createAccount(
        @Body userData: UserData
    ) : Response<UserResponse>

    @GET("users")
    suspend fun getUsers(): Response<UsersResponse>
}