package com.quantumcode.napets.ui.pestDisease.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quantumcode.napets.data.model.PestDisease
import com.quantumcode.napets.data.repository.pestDisease.IPestDiseaseRepository
import com.quantumcode.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PestDiseaseViewModel @Inject constructor(
    private val pestDiseaseRepository: IPestDiseaseRepository
) : BaseViewModel() {

    private val _pestDisease = MutableLiveData<List<PestDisease>>()
    val pestDisease get() = _pestDisease

    fun getPestDisease() {
        viewModelScope.launch {
            val pestDiseaseResponse = pestDiseaseRepository.getPestDisease {
                handleErrorResponse(it)
            }
            _pestDisease.postValue(pestDiseaseResponse)
        }
    }

}