package com.quantumcode.napets.data.repository.home

import com.haroldadmin.cnradapter.NetworkResponse
import com.quantumcode.napets.R
import com.quantumcode.napets.core.service.ApiService
import com.quantumcode.napets.data.model.home.Subject
import com.quantumcode.napets.data.model.home.Weather
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : IHomeRepository {
    override suspend fun getSubjects() = listOf(
        Subject(
            1, "Historial", R.drawable.ic_history_home
        ),
        Subject(
            2, "Plagas y Enfermedades", R.drawable.ic_pests
        ),
        Subject(
            3, "Consejos de Cultivos", R.drawable.ic_tips
        )
    )

    override suspend fun getCurrentWeather(
        latLong: String,
        language: String,
        requestType: String
    ): Weather? {
        val response = apiService.getCurrentWeather(
            latLong = latLong,
            language = language,
            requestType = requestType
        )
        return when(response) {
            is NetworkResponse.Success -> {
                Weather(response.body)
            }
            else -> null
        }
    }
}
