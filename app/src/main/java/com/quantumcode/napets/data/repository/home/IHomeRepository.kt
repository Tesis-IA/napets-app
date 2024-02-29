package com.quantumcode.napets.data.repository.home

import com.quantumcode.napets.data.model.home.Subject
import com.quantumcode.napets.data.model.home.Weather

interface IHomeRepository {

    suspend fun getSubjects(): List<Subject>

    suspend fun getCurrentWeather(latLong: String, language: String, requestType: String) : Weather?
}
