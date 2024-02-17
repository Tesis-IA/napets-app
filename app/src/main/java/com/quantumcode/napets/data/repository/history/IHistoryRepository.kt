package com.quantumcode.napets.data.repository.history

import com.quantumcode.napets.data.model.history.History

interface IHistoryRepository {
    suspend fun getHistoryByDeviceId(deviceId: String, handleErrorHistory: (String) -> Unit) : List<History>
}
