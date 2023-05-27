package com.quantumcode.napets.data.repository.auth

import com.haroldadmin.cnradapter.NetworkResponse
import com.quantumcode.napets.core.ApiService
import com.quantumcode.napets.data.domainmodel.UserResponse
import com.quantumcode.napets.data.model.auth.UserLogin
import com.quantumcode.napets.data.repository.sharedpreferences.ISharedPreferencesRepository
import com.quantumcode.napets.data.utils.Constant
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreferences: ISharedPreferencesRepository
) : IAuthenticationRepository {
    override suspend fun userLogin(
        email: String,
        password: String,
        handleErrorLogin: (String) -> Unit
    ): Boolean {
        val response = apiService.login(
            UserLogin(
                username = email,
                password = password
            )
        )
        val isSuccessfully = when (response) {
            is NetworkResponse.Success -> {
                sharedPreferences.setValue(Constant.TOKEN, response.body.accessToken)
                sharedPreferences.setValue(Constant.EMAIL, response.body.user.email)
                sharedPreferences.setValue(Constant.USER_ID, response.body.user.id)
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