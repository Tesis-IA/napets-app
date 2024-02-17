package com.quantumcode.napets.data.repository.cropsTips

import com.haroldadmin.cnradapter.NetworkResponse
import com.quantumcode.napets.core.service.ApiService
import com.quantumcode.napets.data.model.cropsTips.CropsTips
import javax.inject.Inject

class CropsTipsRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : ICropsTipsRepository {
    override suspend fun getCropsTips(handleErrorResponse: (String) -> Unit): List<CropsTips> {
        return when(val response = apiService.getCropsTips()) {
            is NetworkResponse.Success -> response.body.map { CropsTips(it) }
            else -> {
                handleErrorResponse.invoke("An occurred an error trying to get crop tips")
                listOf()
            }
        }
    }
}
