package com.quantumcode.napets.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quantumcode.napets.core.di.manager.DataStoreManager
import com.quantumcode.napets.data.repository.auth.IAuthenticationRepository
import com.quantumcode.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
) : BaseViewModel() {

    private val _isAuthored = MutableLiveData(false)
    val isAuthored get() = _isAuthored

    fun isAuthored() {
        viewModelScope.launch {
            _isAuthored.postValue(authenticationRepository.isAuthenticate())
        }
    }
}
