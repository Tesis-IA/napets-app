package com.quantumcode.napets.data.repository.auth

import com.haroldadmin.cnradapter.NetworkResponse
import com.quantumcode.napets.core.service.ApiService
import com.quantumcode.napets.core.di.manager.DataStoreManager
import com.quantumcode.napets.data.domainmodel.user.GuestRequest
import com.quantumcode.napets.data.domainmodel.user.UserLoginRequest
import com.quantumcode.napets.data.domainmodel.user.UserRequest
import com.quantumcode.napets.data.domainmodel.user.UserResponse
import com.quantumcode.napets.data.model.auth.UserData
import com.quantumcode.napets.data.utils.PreferencesKeys
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val dataStoreManager: DataStoreManager
) : IAuthenticationRepository {

    override suspend fun userLogin(
        username: String,
        password: String,
        handleErrorLogin: (String) -> Unit
    ): Boolean {
        val response = apiService.login(
            UserLoginRequest(
                username = username,
                password = password
            )
        )
        val isSuccessfully = when (response) {
            is NetworkResponse.Success -> {
                val userData = UserData(response.body)
                dataStoreManager.apply {
                    storeValue(PreferencesKeys.KEY, userData.token)
                    storeValue(PreferencesKeys.EMAIL, userData.email)
                    storeValue(PreferencesKeys.DEVICE_ID, userData.deviceId)
                    storeValue(PreferencesKeys.USERNAME, userData.username)
                    storeValue(PreferencesKeys.USER_ID, userData.id.toString())
                }
                true
            }

            else -> {
                handleErrorLogin.invoke("An occurred a error trying to login")
                false
            }
        }
        return isSuccessfully
    }

    override suspend fun createAccount(
        password: String,
        email: String,
        username: String,
        handleErrorSignup: (String) -> Unit
    ): Boolean {
        val response = apiService.createAccount(
            UserRequest(
                email = email,
                password = password,
                authStrategy = password,
                username = username
            )
        )

        val isSuccessfully = when (response) {
            is NetworkResponse.Success -> {
                val userData = UserData(response.body)
                dataStoreManager.apply {
                    storeValue(PreferencesKeys.KEY, userData.token)
                    storeValue(PreferencesKeys.EMAIL, userData.email)
                    storeValue(PreferencesKeys.DEVICE_ID, userData.deviceId)
                    storeValue(PreferencesKeys.USERNAME, userData.username)
                    storeValue(PreferencesKeys.USER_ID, userData.id.toString())
                }
                true
            }

            else -> {
                handleErrorSignup.invoke("An occurred a error trying to sign up")
                false
            }
        }
        return isSuccessfully
    }

    override suspend fun continueAsGuest(deviceId: String, handleErrorGuest: (String) -> Unit): Boolean {
        val response = apiService.continueAsGuest(
            GuestRequest(device_id = deviceId)
        )
        val isSuccessfully = when (response) {
            is NetworkResponse.Success -> {
                val userData = UserData(response.body)
                dataStoreManager.apply {
                    storeValue(PreferencesKeys.DEVICE_ID, userData.deviceId)
                    storeValue(PreferencesKeys.USER_ID, userData.id.toString())
                }
                true
            }

            else -> {
                handleErrorGuest.invoke("An occurred a error trying to continue as guest")
                false
            }
        }
        return isSuccessfully
    }

    override suspend fun isAuthenticate(): Boolean {
        var isAuthenticated = false
        dataStoreManager.readValue(PreferencesKeys.KEY) {
            isAuthenticated = isNotBlank()
        }
        return isAuthenticated
    }
}
