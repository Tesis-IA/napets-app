package com.quantumcode.napets.core.service

import com.quantumcode.napets.data.domainmodel.user.UserResponse
import com.haroldadmin.cnradapter.NetworkResponse
import com.quantumcode.napets.data.domainmodel.history.HistoryResponse
import com.quantumcode.napets.data.domainmodel.prediction.PredictionResponse
import com.quantumcode.napets.data.domainmodel.user.GuestRequest
import com.quantumcode.napets.data.domainmodel.user.UserLoginRequest
import com.quantumcode.napets.data.domainmodel.user.UserRequest
import com.quantumcode.napets.data.model.ErrorResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

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

    @Multipart
    @POST("prediction/{deviceId}")
    suspend fun makePrediction(
        @Path("deviceId") deviceId: String,
        @Part part: MultipartBody.Part
    ) : NetworkResponse<PredictionResponse, ErrorResponse>

    @GET("history/user/{deviceId}")
    suspend fun getUserById(
        @Path("deviceId") deviceId: String
    ) : NetworkResponse<List<HistoryResponse>, ErrorResponse>
}
