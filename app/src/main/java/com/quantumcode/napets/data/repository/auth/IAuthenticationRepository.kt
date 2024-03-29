package com.quantumcode.napets.data.repository.auth

import com.quantumcode.napets.data.domainmodel.user.UserResponse

interface IAuthenticationRepository {
    suspend fun userLogin(email: String, password: String, deviceId: String, handleErrorLogin: (String) -> Unit): Boolean

    suspend fun createAccount(password: String, email: String, username: String, deviceId: String, handleErrorSignup: (String) -> Unit): Boolean

    suspend fun continueAsGuest(deviceId: String, handleErrorGuest: (String) -> Unit): Boolean

    suspend fun isAuthenticate(): Boolean
}
