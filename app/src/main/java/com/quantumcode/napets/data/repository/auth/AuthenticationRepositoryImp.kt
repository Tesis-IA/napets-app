package com.quantumcode.napets.data.repository.auth

import com.haroldadmin.cnradapter.NetworkResponse
import com.quantumcode.napets.core.ApiService
import com.quantumcode.napets.core.di.manager.DataStoreManager
import com.quantumcode.napets.data.domainmodel.user.UserLoginRequest
import com.quantumcode.napets.data.domainmodel.user.UserResponse
import com.quantumcode.napets.data.model.auth.UserData
import com.quantumcode.napets.data.utils.PreferencesKeys
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val dataStoreManager: DataStoreManager
) : IAuthenticationRepository {
    override suspend fun userLogin(
        email: String,
        password: String,
        handleErrorLogin: (String) -> Unit
    ): Boolean {
        val response = apiService.login(
            UserLoginRequest(
                email = email,
                password = password
            )
        )
        val isSuccessfully = when (response) {
            is NetworkResponse.Success -> {
                val userData = UserData(response.body)
                dataStoreManager.storeValue(PreferencesKeys.KEY, userData.token)
                dataStoreManager.storeValue(PreferencesKeys.EMAIL, userData.email)
                dataStoreManager.storeValue(PreferencesKeys.USER_ID, userData.id.toString())
                true
            }

            is NetworkResponse.ServerError -> {
                handleErrorLogin.invoke(response.error?.message.orEmpty())
                false
            }

            is NetworkResponse.NetworkError -> {
                handleErrorLogin.invoke(response.error.message.orEmpty())
                false
            }

            is NetworkResponse.UnknownError -> {
                handleErrorLogin.invoke(response.error.message.orEmpty())
                false
            }
        }
        return isSuccessfully
    }

    override suspend fun createAccount(
        password: String,
        email: String,
        username: String
    ): UserResponse {
        TODO("Not yet implemented")
    }
}