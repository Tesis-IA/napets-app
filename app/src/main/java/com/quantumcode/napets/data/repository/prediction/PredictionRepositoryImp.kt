package com.quantumcode.napets.data.repository.prediction

import com.haroldadmin.cnradapter.NetworkResponse
import com.quantumcode.napets.core.service.ApiService
import com.quantumcode.napets.data.domainmodel.prediction.PredictionResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class PredictionRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : IPredictionRepository {
    override suspend fun makePrediction(
        deviceId: String,
        file: File,
        handleErrorPredict: (String) -> Unit
    ): PredictionResponse? {
        val requestFile = MultipartBody.Part.createFormData(
            "file",
            file.name,
            file.asRequestBody()
        )
        val response = apiService.makePrediction(
            deviceId = deviceId,
            part = requestFile
        )
        return when (response) {
            is NetworkResponse.Success -> response.body

            else -> {
                handleErrorPredict.invoke("An occurred an error trying to predict image")
                null
            }
        }
    }
}
