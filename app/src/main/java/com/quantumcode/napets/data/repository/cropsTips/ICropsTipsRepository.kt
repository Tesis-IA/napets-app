package com.quantumcode.napets.data.repository.cropsTips

import com.quantumcode.napets.data.model.cropsTips.CropsTips

interface ICropsTipsRepository {
    suspend fun getCropsTips(handleErrorResponse: (String) -> Unit) : List<CropsTips>
}
