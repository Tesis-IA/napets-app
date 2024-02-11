package com.quantumcode.napets.core.service

import com.quantumcode.napets.data.domainmodel.user.UserResponse
import com.haroldadmin.cnradapter.NetworkResponse
import com.quantumcode.napets.data.domainmodel.user.GuestRequest
import com.quantumcode.napets.data.domainmodel.user.UserLoginRequest
import com.quantumcode.napets.data.domainmodel.user.UserRequest
import com.quantumcode.napets.data.model.ErrorResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body userLogin: UserLoginRequest
    ): NetworkResponse<UserResponse, ErrorResponse>

    @POST("auth/register")
    suspend fun createAccount(
        @Body userRequest: UserRequest
    ) : NetworkResponse<UserResponse, ErrorResponse>

    @POST("auth/guest")
    suspend fun continueAsGuest(
        @Body guestRequest: GuestRequest
    ) : NetworkResponse<UserResponse, ErrorResponse>
}
