package com.quantumcode.napets.data.repository.history

import com.haroldadmin.cnradapter.NetworkResponse
import com.quantumcode.napets.core.service.ApiService
import com.quantumcode.napets.data.model.history.History
import javax.inject.Inject

class HistoryRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : IHistoryRepository {
    override suspend fun getHistoryByDeviceId(
        deviceId: String,
        handleErrorHistory: (String) -> Unit
    ): List<History> {
        val response = apiService.getUserById(
            deviceId = deviceId
        )

        return when(response) {
            is NetworkResponse.Success -> response.body.map { History(it) }
            else -> {
                handleErrorHistory.invoke("An occurred an error trying to get history")
                listOf()
            }
        }
    }
}
