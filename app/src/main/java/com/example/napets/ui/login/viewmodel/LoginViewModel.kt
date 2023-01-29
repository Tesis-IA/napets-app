package com.example.napets.ui.login.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.napets.data.repository.AuthenticationRepositoryImp
import com.example.napets.data.repository.IAuthenticationRepository
import com.example.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
): BaseViewModel() {

    override var showLoading = View.GONE

    fun login(email: String, password: String){
        runBlockingCoroutine(Dispatchers.IO){
            authenticationRepository.userLogin(email, password)
        }
    }


}