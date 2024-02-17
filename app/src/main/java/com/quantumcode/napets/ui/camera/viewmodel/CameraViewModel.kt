package com.quantumcode.napets.ui.camera.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quantumcode.napets.data.model.prediction.Prediction
import com.quantumcode.napets.data.repository.prediction.IPredictionRepository
import com.quantumcode.napets.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val predictionRepository: IPredictionRepository
) : BaseViewModel() {

    private var _prediction = MutableLiveData<Prediction>()
    val prediction get() = _prediction

    fun makePrediction(
        deviceId: String,
        file: File
    ) {
        viewModelScope.launch {
            val predictionResult = predictionRepository.makePrediction(
                deviceId = deviceId,
                file = file,
                handleErrorPredict = ::handleErrorResponse
            )
            if (predictionResult == null) {
                handleErrorResponse("An occurred an error trying to predict image")
            } else {
                val stablePredictionResponse = Prediction(predictionResult)
                _prediction.postValue(stablePredictionResponse)
            }
        }
    }
}
