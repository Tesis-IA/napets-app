package com.quantumcode.napets.ui.main.viewmodel

import com.quantumcode.napets.data.repository.IAuthenticationRepository
import com.quantumcode.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
) : BaseViewModel() {


    fun getUsers(){
        runBlockingCoroutine(Dispatchers.IO){
            authenticationRepository.getUsers()
        }
    }

}