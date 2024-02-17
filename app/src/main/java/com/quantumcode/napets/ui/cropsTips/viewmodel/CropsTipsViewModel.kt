package com.quantumcode.napets.ui.cropsTips.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quantumcode.napets.data.model.cropsTips.CropsTips
import com.quantumcode.napets.data.repository.cropsTips.ICropsTipsRepository
import com.quantumcode.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CropsTipsViewModel @Inject constructor(
    private val cropsTipsRepository: ICropsTipsRepository
) : BaseViewModel() {

    private val _cropsTips = MutableLiveData<List<CropsTips>>()
    val cropsTips get() = _cropsTips

    fun getCropsTips() {
        viewModelScope.launch {
            val cropsTipsResponse = cropsTipsRepository.getCropsTips {
                handleErrorResponse(it)
            }

            _cropsTips.postValue(cropsTipsResponse)
        }
    }
}