package com.example.napets.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.napets.data.domainmodel.UserResponse
import com.example.napets.data.repository.IAuthenticationRepository
import com.example.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
) : BaseViewModel() {

    private val _isAuthenticated = MutableLiveData(false)
    val isAuthenticated get() = _isAuthenticated

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse get() = _errorResponse

    fun login(email: String, password: String) {
        runBlockingCoroutine(Dispatchers.IO){
            val result = authenticationRepository.userLogin(
                email = email,
                password = password
            )
            val canGetUser = when{
                result.isSuccessful && result.body()?.accessToken?.isNotEmpty() == true ->{
                    true
                }
                else ->{
                    _errorResponse.postValue(result.message())
                    false
                }
            }
            _isAuthenticated.postValue(canGetUser)
        }
        showLoading = false
    }


}