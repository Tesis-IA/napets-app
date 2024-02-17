package com.quantumcode.napets.data.repository.pestDisease

import com.haroldadmin.cnradapter.NetworkResponse
import com.quantumcode.napets.core.service.ApiService
import com.quantumcode.napets.data.model.PestDisease
import javax.inject.Inject

class PestDiseaseRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : IPestDiseaseRepository {
    override suspend fun getPestDisease(handleErrorResponse: (String) -> Unit): List<PestDisease> {

        return when (val response = apiService.getPestDisease()) {
            is NetworkResponse.Success -> response.body.map { PestDisease(it) }
            else -> {
                handleErrorResponse.invoke("An occurred an error trying to get history")
                listOf()
            }
        }
    }
}
