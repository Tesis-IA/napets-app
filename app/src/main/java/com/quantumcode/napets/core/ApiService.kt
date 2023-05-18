package com.quantumcode.napets.core

import com.quantumcode.napets.data.domainmodel.ErrorResponse
import com.quantumcode.napets.data.domainmodel.UsersResponse
import com.quantumcode.napets.data.domainmodel.UserResponse
import com.quantumcode.napets.data.model.UserData
import com.quantumcode.napets.data.model.UserLogin
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body userLogin: UserLogin
    ): Response<UserResponse>

    @POST("createUser")
    suspend fun createAccount(
        @Body userData: UserData
    ) : Response<UserResponse>

    @GET("users")
    suspend fun getUsers(): NetworkResponse<UsersResponse, ErrorResponse>
}