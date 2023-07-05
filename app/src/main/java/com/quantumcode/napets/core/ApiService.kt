package com.quantumcode.napets.core

import com.quantumcode.napets.data.domainmodel.user.UserResponse
import com.haroldadmin.cnradapter.NetworkResponse
import com.quantumcode.napets.data.domainmodel.user.UserLoginRequest
import com.quantumcode.napets.data.domainmodel.user.UserRequest
import com.quantumcode.napets.data.model.ErrorResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body userLogin: UserLoginRequest
    ): NetworkResponse<UserResponse, ErrorResponse>

    @POST("createUser")
    suspend fun createAccount(
        @Body userRequest: UserRequest
    ) : Response<UserResponse>
}