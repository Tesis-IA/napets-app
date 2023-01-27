package com.example.napets.ui.login.viewmodel

import com.example.napets.data.repository.AuthenticationRepositoryImp
import com.example.napets.data.repository.IAuthenticationRepository
import com.example.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
): BaseViewModel() {

}