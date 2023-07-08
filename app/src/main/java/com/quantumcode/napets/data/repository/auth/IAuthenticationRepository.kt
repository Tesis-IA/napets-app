package com.quantumcode.napets.data.repository.auth

import com.quantumcode.napets.data.domainmodel.user.UserResponse

interface IAuthenticationRepository {
    suspend fun userLogin(email: String, password: String, handleErrorLogin: (String) -> Unit): Boolean

    suspend fun createAccount(password: String, email: String, username: String): UserResponse

    suspend fun isAuthenticate(): Boolean
}
