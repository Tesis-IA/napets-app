package com.quantumcode.napets.ui.history.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quantumcode.napets.data.model.history.History
import com.quantumcode.napets.data.repository.history.IHistoryRepository
import com.quantumcode.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: IHistoryRepository
) : BaseViewModel() {

    private val _history = MutableLiveData<List<History>>()
    val history get() = _history

    fun getHistoryByUserId(deviceId: String) {
        viewModelScope.launch {
            val historyResponse = historyRepository.getHistoryByDeviceId(
                deviceId = deviceId,
                handleErrorHistory = ::handleErrorResponse
            )
            history.postValue(historyResponse)
        }
    }
}
