package com.quantumcode.napets.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quantumcode.napets.data.repository.auth.IAuthenticationRepository
import com.quantumcode.napets.data.utils.validatePassword
import com.quantumcode.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
) : BaseViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _isAuthenticated = MutableLiveData(false)
    val isAuthenticated get() = _isAuthenticated

    private fun login(username: String, password: String, deviceId: String) {
        viewModelScope.launch {
            val isSuccess = authenticationRepository.userLogin(
                email = username,
                password = password,
                deviceId = deviceId,
                handleErrorLogin = ::handleErrorResponse
            )
            _isAuthenticated.postValue(isSuccess)
        }
        showLoading = false
    }

    fun continueAsGuest(deviceId: String) {
        viewModelScope.launch {
            val isSuccess = authenticationRepository.continueAsGuest(
                deviceId = deviceId,
                handleErrorGuest = ::handleErrorResponse
            )
            _isAuthenticated.postValue(isSuccess)
        }
    }

    fun validateCredentials(
        usernameToValidate: String,
        passwordToValidate: String,
        deviceId: String
    ) {
        when {
            usernameToValidate.isEmpty() && !passwordToValidate.validatePassword() -> {
                password.postValue("La contraseña debe tener un mínimo de 8 caracteres")
                username.postValue("Campo requerido")
            }
            usernameToValidate.isEmpty() -> {
                username.postValue("Campo requerido")
            }
            !passwordToValidate.validatePassword() -> {
                password.postValue("La contraseña debe tener un mínimo de 8 caracteres")
            }
            else -> {
                password.postValue("")
                username.postValue("")
                login(usernameToValidate, passwordToValidate, deviceId)
            }
        }
    }
}
