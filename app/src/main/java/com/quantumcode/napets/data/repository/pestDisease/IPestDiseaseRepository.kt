package com.quantumcode.napets.data.repository.pestDisease

import com.quantumcode.napets.data.model.PestDisease

interface IPestDiseaseRepository {
    suspend fun getPestDisease(handleErrorResponse: (String) -> Unit) : List<PestDisease>
}
