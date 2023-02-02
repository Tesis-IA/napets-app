package com.example.napets.ui.login.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.napets.data.domainmodel.UserResponse
import com.example.napets.data.repository.AuthenticationRepositoryImp
import com.example.napets.data.repository.IAuthenticationRepository
import com.example.napets.ui.base.BaseViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
): BaseViewModel() {

    private val _userData = MutableLiveData<UserResponse?>()
    val userData get() = _userData

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse get() = _errorResponse

    fun login(email: String, password: String){
        runBlockingCoroutine(Dispatchers.IO){
            when(val response = authenticationRepository.userLogin(email, password)){
                is NetworkResponse.Success -> {
                    _userData.postValue(response.body)
                }
                is NetworkResponse.NetworkError -> {
                    _errorResponse.postValue(response.error.message)
                }
                else -> { /* no-op*/ }
            }
        }
        showLoading = false
    }


}