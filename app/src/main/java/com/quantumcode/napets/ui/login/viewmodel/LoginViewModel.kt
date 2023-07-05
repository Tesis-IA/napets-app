package com.quantumcode.napets.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quantumcode.napets.data.repository.auth.IAuthenticationRepository
import com.quantumcode.napets.data.utils.validateEmail
import com.quantumcode.napets.data.utils.validatePassword
import com.quantumcode.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
) : BaseViewModel() {

    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()

    private val _isAuthenticated = MutableLiveData(false)
    val isAuthenticated get() = _isAuthenticated

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse get() = _errorResponse

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            val isSuccess = authenticationRepository.userLogin(
                email = email,
                password = password,
                handleErrorLogin = ::handleErrorLogin
            )
            _isAuthenticated.postValue(isSuccess)
        }
        showLoading = false
    }

    private fun handleErrorLogin(message: String) {
        _errorResponse.postValue(message)
    }

    fun validateCredentials(emailToValidate: String, passwordToValidate: String) {
        when {
            !emailToValidate.validateEmail() && !passwordToValidate.validatePassword() -> {
                password.postValue("La contraseña debe tener un mínimo de 8 caracteres")
                email.postValue("Ingrese un correo válido")
            }
            !emailToValidate.validateEmail() -> {
                email.postValue("Ingrese un correo válido")
            }
            !passwordToValidate.validatePassword() -> {
                password.postValue("La contraseña debe tener un mínimo de 8 caracteres")
            }
            else -> {
                password.postValue("")
                email.postValue("")
                login(emailToValidate, passwordToValidate)
            }
        }
    }
}
