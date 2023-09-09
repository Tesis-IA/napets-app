package com.quantumcode.napets.core

import com.quantumcode.napets.data.domainmodel.user.UserResponse
import com.haroldadmin.cnradapter.NetworkResponse
import com.quantumcode.napets.data.domainmodel.user.UserLoginRequest
import com.quantumcode.napets.data.domainmodel.user.UserRequest
import com.quantumcode.napets.data.model.ErrorResponse
import com.quantumcode.napets.data.model.home.Subject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body userLogin: UserLoginRequest
    ): NetworkResponse<UserResponse, ErrorResponse>

    @POST("auth/register")
    suspend fun createAccount(
        @Body userRequest: UserRequest
    ) : Response<UserResponse>

    @GET("subjects")
    suspend fun getSubjects() : Response<List<Subject>>
}
