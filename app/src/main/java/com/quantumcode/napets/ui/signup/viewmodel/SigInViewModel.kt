package com.quantumcode.napets.ui.signup.viewmodel

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
class SigInViewModel @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
): BaseViewModel() {

    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val username = MutableLiveData<String>()

    private val _isAuthenticated = MutableLiveData(false)
    val isAuthenticated get() = _isAuthenticated

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse get() = _errorResponse

    private fun registerUSer(username: String, email: String, password: String){
        viewModelScope.launch {
            val isSuccess = authenticationRepository.createAccount(
                username = username,
                email = email,
                password = password,
                handleErrorSignup = ::handleErrorUser
            )
            _isAuthenticated.postValue(isSuccess)
        }
    }

    private fun handleErrorUser(message: String) {
        _errorResponse.postValue(message)
    }

    fun validateCredentials(usernameToValidate: String, emailToValidate: String, passwordToValidate: String) {
        when {
            !emailToValidate.validateEmail() && !passwordToValidate.validatePassword() && usernameToValidate.isEmpty() -> {
                password.postValue("La contraseña debe tener un mínimo de 8 caracteres")
                email.postValue("Ingrese un correo válido")
                username.postValue("Campo requerido")
            }
            !emailToValidate.validateEmail() -> {
                email.postValue("Ingrese un correo válido")
            }
            !passwordToValidate.validatePassword() -> {
                password.postValue("La contraseña debe tener un mínimo de 8 caracteres")
            }
            usernameToValidate.isEmpty() -> {
                username.postValue("Campo requerido")
            }
            else -> {
                password.postValue("")
                email.postValue("")
                username.postValue("")
                registerUSer(usernameToValidate, emailToValidate, passwordToValidate)
            }
        }
    }
}
