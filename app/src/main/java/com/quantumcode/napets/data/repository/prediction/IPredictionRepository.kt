package com.quantumcode.napets.data.repository.prediction

import com.quantumcode.napets.data.domainmodel.prediction.PredictionResponse
import java.io.File

interface IPredictionRepository {

    suspend fun makePrediction(deviceId: String, file: File, handleErrorPredict: (String) -> Unit) : PredictionResponse?
}