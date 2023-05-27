package com.quantumcode.napets.ui.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.quantumcode.napets.data.repository.IAuthenticationRepository
import com.quantumcode.napets.data.utils.validateEmail
import com.quantumcode.napets.data.utils.validatePassword
import com.quantumcode.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.math.log

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
        runBlockingCoroutine(Dispatchers.IO){
            val result = authenticationRepository.userLogin(
                email = email,
                password = password
            )
            Log.i("success", result.toString())
        }
        showLoading = false
    }

    fun validateCredential(emailToValidate: String, passwordToValidate: String) {
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