package com.quantumcode.napets.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quantumcode.napets.data.model.home.Subject
import com.quantumcode.napets.data.repository.home.IHomeRepository
import com.quantumcode.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: IHomeRepository
): BaseViewModel() {

    private val _subjects = MutableLiveData<List<Subject>>()
    val subject get() = _subjects

    fun getSubjects() {
        viewModelScope.launch {
            val subjects = homeRepository.getSubjects()
            _subjects.postValue(subjects)
        }
    }
}
