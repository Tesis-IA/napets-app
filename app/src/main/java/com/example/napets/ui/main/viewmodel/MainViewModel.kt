package com.example.napets.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.napets.data.repository.AuthenticationRepositoryImp
import com.example.napets.data.repository.IAuthenticationRepository
import com.example.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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